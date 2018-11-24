package com.ashu.ms.common.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.ashu.ms.common.Util.JsonUtils;
import com.ashu.ms.common.logging.Api;
import com.ashu.ms.common.logging.LoggingFields;
import com.ashu.ms.common.logging.Request;
import com.ashu.ms.common.logging.Response;

public class LoggingFilter extends AbstractOperations implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

	private String serviceName;
	private String traceApiUrl;

	public LoggingFilter(String serviceName, String traceApiUrl) {
		this.serviceName = serviceName;
		this.traceApiUrl = traceApiUrl;
	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
			ServletException {

		Date startTime = new Date();
		ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
		ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
		filterChain.doFilter(wrapperRequest, wrapperResponse);
		Date endTime = new Date();
		String requestBody = new String(wrapperRequest.getContentAsByteArray());
		Map<String, Collection<String>> reqHeaders = getAllHeadersFromRequest(wrapperRequest);
		byte[] responseContent = wrapperResponse.getContentAsByteArray();
		servletResponse.getOutputStream().write(responseContent);
		Map<String, Collection<String>> responseHeader = getAllHeadersFromResponse(wrapperResponse);
		String responseBody = new String(responseContent);

		int httpStatus = wrapperResponse.getStatus();
		Api api = new Api.ApiBuilder().api(wrapperRequest.getRequestURI()).endTime(endTime).srartTime(startTime).serviceName(serviceName).build();
		Request request = new Request.RequestBuilder().body(JsonUtils.convertStringToObject(requestBody)).headers(reqHeaders)
				.attributes(wrapperRequest.getParameterMap()).build();
		Response response = new Response.ResponseBuilder().headers(responseHeader).statusCode(httpStatus)
				.responseBody(JsonUtils.convertStringToObject(responseBody)).build();

		LoggingFields fields = new LoggingFields.LoggingFieldBuilder().api(api).request(request).response(response).build();
		LOGGER.info("Trace Log: " + JsonUtils.toJsonString(fields));

		if (traceApiUrl != null) {
			new PostLogToTrace(traceApiUrl, fields).postToServer();
		} else {
			LOGGER.error("traceApiUrl is null please rerun the application with propert congfiguration");
		}
	}

	@Override
	public void destroy() {
	}
}
