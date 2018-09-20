package com.kinexcs.demo.common.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractOperations {

	protected Map<String, Collection<String>> getAllHeadersFromRequest(HttpServletRequest servletRequest) {
		Map<String, Collection<String>> headerMap = new HashMap<>();
		Enumeration<String> headerNames = servletRequest.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String header = headerNames.nextElement();
				Enumeration<String> headervalues = servletRequest.getHeaders(header);
				List<String> valueList = new ArrayList<String>();
				while (headervalues.hasMoreElements()) {
					String headerValue = headervalues.nextElement();
					valueList.add(headerValue);
				}
				headerMap.put(header, valueList);
			}
		}
		return headerMap;
	}

	protected Map<String, Collection<String>> getAllHeadersFromResponse(HttpServletResponse httpServletResponse) {
		Map<String, Collection<String>> headerMap = new HashMap<>();
		Collection<String> headerNames = httpServletResponse.getHeaderNames();
		if (headerNames != null) {
			for (String header : headerNames) {
				headerMap.put(header, httpServletResponse.getHeaders(header));
			}
		}
		return headerMap;
	}

}
