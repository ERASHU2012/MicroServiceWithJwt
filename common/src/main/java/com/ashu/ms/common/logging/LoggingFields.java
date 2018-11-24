package com.ashu.ms.common.logging;

public class LoggingFields {

	private Api api;
	private Request request;
	private Response response;

	public LoggingFields() {
	}

	public LoggingFields(Api api, Request request, Response response) {
		super();
		this.api = api;
		this.request = request;
		this.response = response;
	}

	public void setApi(Api api) {
		this.api = api;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Api getApi() {
		return api;
	}

	public Request getRequest() {
		return request;
	}

	public Response getResponse() {
		return response;
	}

	public static class LoggingFieldBuilder {

		private Api api;
		private Request request;
		private Response response;

		public LoggingFieldBuilder api(Api api) {
			this.api = api;
			return this;
		}

		public LoggingFieldBuilder request(Request request) {
			this.request = request;
			return this;
		}

		public LoggingFieldBuilder response(Response response) {
			this.response = response;
			return this;
		}

		public LoggingFields build() {
			return new LoggingFields(api, request, response);
		}
	}
}
