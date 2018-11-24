package com.ashu.ms.common.Util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ashu.ms.common.constant.ApiConstent;
import com.ashu.ms.common.constant.DataNotFoundException;
import com.ashu.ms.common.dto.ConfigPropertyGetRequest;
import com.ashu.ms.common.dto.ConfigPropertyGetResponse;

public class GetPropertyFromConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetPropertyFromConfig.class);

	public static String getConfigPeopertyValue(String configApiUrl, String propertyName) throws DataNotFoundException {
		LOGGER.info("going to fetch property (" + propertyName + ") from config api: " + configApiUrl);
		String uri = UriComponentsBuilder.fromHttpUrl(configApiUrl).queryParam(ApiConstent.Header.PROPERTY_NAME, propertyName).toUriString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ConfigPropertyGetRequest> entity = new HttpEntity<>(headers);
		ResponseEntity<ConfigPropertyGetResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity, ConfigPropertyGetResponse.class);

		String propertyValue = result.getBody().getPropertyValue();
		if (propertyValue == null) {
			throw new DataNotFoundException("property (" + propertyName + ") not founded at config api: " + configApiUrl);
		}
		return propertyValue;
	}
}
