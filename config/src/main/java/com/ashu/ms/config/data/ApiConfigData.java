package com.ashu.ms.config.data;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ashu.ms.common.Util.JsonUtils;
import com.ashu.ms.common.constant.ApiConstent;

public class ApiConfigData {

	private final static Logger LOGGER = LoggerFactory.getLogger(ApiConfigData.class);

	private static Map<String, String> allConfig = new HashMap<>();

	static {
		allConfig.put(ApiConstent.Config.ORDER_API_GET_PRODUCT_URL, "http://localhost:9002/order/getProduct");
		allConfig.put(ApiConstent.Config.PRODUCT_API_GET_PRODUCT_URL, "http://localhost:9003/product/getProduct");
		allConfig.put(ApiConstent.Config.TRACE_API_PUT_LOG_URL, "http://localhost:9001/trace/setTrace");

	}

	public static void addConfiguration(Map<String, String> configuration) {
		allConfig.putAll(configuration);
		LOGGER.info("Config Data is refreshed final data is: " + JsonUtils.toJsonString(allConfig));

	}

	public static void clearAllConfig() {
		allConfig.clear();
		LOGGER.info("Config Data is cleared");

	}

	public static String getConfig(String property) {
		return allConfig.get(property);
	}
}
