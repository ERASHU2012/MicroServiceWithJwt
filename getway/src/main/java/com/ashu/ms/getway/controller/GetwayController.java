package com.ashu.ms.getway.controller;

import io.swagger.annotations.Api;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ashu.ms.common.Util.GetPropertyFromConfig;
import com.ashu.ms.common.constant.ApiConstent;
import com.ashu.ms.common.constant.DataNotFoundException;
import com.ashu.ms.common.dto.Product;

@Api
@RestController
@RequestMapping("/getway")
public class GetwayController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetwayController.class);

	@Value("${config.api.url}")
	private String configApiUrl;

	private String productApiUrl;

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Product> getProduct(@RequestParam(name = ApiConstent.Header.PRODUCT_ID) long productId) throws DataNotFoundException {
		Product product = callProductMs(productId, false);
//		Product product =new Product();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProductWithDetails", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Product> getProductWithDetails(@RequestParam(name = ApiConstent.Header.PRODUCT_ID) long productId)
			throws DataNotFoundException {
		Product product = callProductMs(productId, true);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	private Product callProductMs(long productId, boolean additionalDataRequired) throws DataNotFoundException {
		if (productApiUrl == null) {
			getProductUrlFromConfig();
		}
		String uri = UriComponentsBuilder.fromHttpUrl(productApiUrl).queryParam(ApiConstent.Header.PRODUCT_ID, productId)
				.queryParam(ApiConstent.Header.ORDER_ADDITIONAL_DATA_REQUIRED, additionalDataRequired).toUriString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Product> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Product.class);
		return result.getBody();
	}

	private void getProductUrlFromConfig() throws DataNotFoundException {
		LOGGER.info("going to call config for ORDER-MS  URL");
		productApiUrl = GetPropertyFromConfig.getConfigPeopertyValue(configApiUrl, ApiConstent.Config.PRODUCT_API_GET_PRODUCT_URL);
	}
}
