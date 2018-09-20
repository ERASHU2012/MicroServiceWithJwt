package com.kinexcs.demo.common.logging;

import java.util.Collection;
import java.util.Map;

public class Request {

	private Object body;
	private Map<String, Collection<String>> headers;
	private Map<String, String[]> attributes;

	public Request() {
	}

	public Request(Object body, Map<String, Collection<String>> headers, Map<String, String[]> attributes) {
		super();
		this.body = body;
		this.headers = headers;
		this.attributes = attributes;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public void setHeaders(Map<String, Collection<String>> headers) {
		this.headers = headers;
	}

	public void setAttributes(Map<String, String[]> attributes) {
		this.attributes = attributes;
	}

	public Object getBody() {
		return body;
	}

	public Map<String, Collection<String>> getHeaders() {
		return headers;
	}

	public Map<String, String[]> getAttributes() {
		return attributes;
	}

	public static class RequestBuilder {
		private Object body;
		private Map<String, Collection<String>> headers;
		private Map<String, String[]> attributes;

		public RequestBuilder body(Object body) {
			this.body = body;
			return this;
		}

		public RequestBuilder headers(Map<String, Collection<String>> headers) {
			this.headers = headers;
			return this;
		}

		public RequestBuilder attributes(Map<String, String[]> attributes) {
			this.attributes = attributes;
			return this;
		}

		public Request build() {
			return new Request(body, headers, attributes);
		}
	}

}
