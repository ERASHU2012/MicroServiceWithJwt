package com.ashu.ms.trace.controller;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.ms.common.Util.JsonUtils;
import com.ashu.ms.common.logging.LoggingFields;

@Api
@RestController
@RequestMapping("/trace")
public class TraceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TraceController.class);

	private static List<LoggingFields> DATA_LIST = new ArrayList<LoggingFields>();

	@RequestMapping(value = "/setTrace", method = RequestMethod.PUT)
	public void setData(@RequestBody LoggingFields fields) {
		LOGGER.info("trace recorded: " + JsonUtils.toJsonString(fields));
		DATA_LIST.add(fields);
	}

	@RequestMapping(value = "/getTrace", method = RequestMethod.GET)
	public ResponseEntity<List<LoggingFields>> getData() {
		LOGGER.info("trace returned");
		return new ResponseEntity<>(DATA_LIST, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteTrace", method = RequestMethod.DELETE)
	public void clear() {
		DATA_LIST.clear();
		LOGGER.info("trace deleted");
	}

}
