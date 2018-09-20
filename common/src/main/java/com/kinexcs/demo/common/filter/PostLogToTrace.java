package com.kinexcs.demo.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.kinexcs.demo.common.logging.LoggingFields;

public class PostLogToTrace implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostLogToTrace.class);

	public String traceApiUrl;
	private LoggingFields loggingFields;

	public PostLogToTrace(String traceApiUrl, LoggingFields loggingFields) {
		super();
		this.loggingFields = loggingFields;
		this.traceApiUrl = traceApiUrl;
	}

	@Override
	public void run() {
		try {
			LOGGER.info("Going to send log to trace: " + traceApiUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<LoggingFields> entity = new HttpEntity<>(this.loggingFields, headers);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.put(traceApiUrl, entity);
			LOGGER.info("log send to trace successfully..");
		} catch (HttpClientErrorException e) {
			LOGGER.error("Unable to send log to trace api ", e);
		}
	}

	public void postToServer() {
		new Thread(this).start();
	}
}
