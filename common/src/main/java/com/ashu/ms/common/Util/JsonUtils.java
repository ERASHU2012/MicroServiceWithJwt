package com.ashu.ms.common.Util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

	public static String toJsonString(Object o) {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = mapperObj.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to convert to String object ", e);
		}
		return jsonStr;
	}

	public static JsonNode convertStringToObject(String jsonString) {
		if((jsonString==null) || (jsonString.isEmpty())) {
			return null;
		}
		JsonNode actualObj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			actualObj = mapper.readTree(jsonString);
		} catch (IOException e) {
			LOGGER.error("Unable to parse json " + jsonString, e);
		}
		return actualObj;
	}
}