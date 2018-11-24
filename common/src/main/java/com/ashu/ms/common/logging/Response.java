package com.ashu.ms.common.logging;

import java.util.Collection;
import java.util.Map;

public class Response {

	private int statusCode;
	private Map<String, Collection<String>> headers;
	private Object responseBody;

	public Response() {
	}

	public Response(int statusCode, Map<String, Collection<String>> headers, Object responseBody) {
		this.statusCode = statusCode;
		this.headers = headers;
		this.responseBody = responseBody;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, Collection<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Collection<String>> headers) {
		this.headers = headers;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public static class ResponseBuilder {
		private int statusCode;
		private Map<String, Collection<String>> headers;
		private Object responseBody;

		public ResponseBuilder() {

		}

		public ResponseBuilder statusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public ResponseBuilder headers(Map<String, Collection<String>> headers) {
			this.headers = headers;
			return this;
		}

		public ResponseBuilder responseBody(Object responseBody) {
			this.responseBody = responseBody;
			return this;
		}

		public Response build() {
			return new Response(statusCode, headers, responseBody);
		}
	}
}
