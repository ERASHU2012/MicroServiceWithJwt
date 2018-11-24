package com.ashu.ms.config.controller;

import io.swagger.annotations.Api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.ms.common.constant.ApiConstent;
import com.ashu.ms.common.dto.ConfigPropertyGetResponse;
import com.ashu.ms.config.data.ApiConfigData;

@Api
@RestController
@RequestMapping("/config")
public class ConfigController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

	@RequestMapping(value = "/setConfig", method = RequestMethod.PUT)
	public void setConfig(@RequestBody Map<String, String> configData) {
		ApiConfigData.addConfiguration(configData);
	}

	@RequestMapping(value = "/getConfig", method = RequestMethod.GET)
	public ResponseEntity<ConfigPropertyGetResponse> getConfig(@RequestParam(name = ApiConstent.Header.PROPERTY_NAME) String propertyName) {
		LOGGER.info("Request to get property value for: " + propertyName);
		String propertyValue = ApiConfigData.getConfig(propertyName);
		ConfigPropertyGetResponse propResponse = new ConfigPropertyGetResponse();
		propResponse.setPropertyName(propertyName);
		propResponse.setPropertyValue(propertyValue);
		return new ResponseEntity<>(propResponse, HttpStatus.OK);

	}
}
