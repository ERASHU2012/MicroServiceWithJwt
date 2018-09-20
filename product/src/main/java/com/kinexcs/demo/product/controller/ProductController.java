package com.kinexcs.demo.product.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kinexcs.demo.common.Util.GetPropertyFromConfig;
import com.kinexcs.demo.common.constant.ApiConstent;
import com.kinexcs.demo.common.constant.DataNotFoundException;
import com.kinexcs.demo.common.dto.Product;

@Api
@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	@Value("${config.api.url}")
	private String configApiUrl;

	private String orderMsUrl;

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@RequestParam(name = ApiConstent.Header.PRODUCT_ID) long productId,
			@RequestParam(name = ApiConstent.Header.ORDER_ADDITIONAL_DATA_REQUIRED) boolean additionalDataRequired) throws DataNotFoundException {

		Product product = callOrderMs(productId, additionalDataRequired);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	private Product callOrderMs(long productId, boolean additionalDataRequired) throws DataNotFoundException {
		if (orderMsUrl == null) {
			getOrderUrl();
		}
		String uri = UriComponentsBuilder.fromHttpUrl(orderMsUrl).queryParam(ApiConstent.Header.PRODUCT_ID, productId)
				.queryParam(ApiConstent.Header.ORDER_ADDITIONAL_DATA_REQUIRED, additionalDataRequired).toUriString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Product> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Product.class);
		return result.getBody();
	}

	private void getOrderUrl() throws DataNotFoundException {
		LOGGER.info("going to call config for ORDER-MS  URL");
		orderMsUrl = GetPropertyFromConfig.getConfigPeopertyValue(configApiUrl, ApiConstent.Config.ORDER_API_GET_PRODUCT_URL);
	}
}
