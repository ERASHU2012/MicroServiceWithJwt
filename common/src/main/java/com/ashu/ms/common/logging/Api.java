package com.ashu.ms.common.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Api {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");

	private String serviceName;
	private String api;
	private String startTime;
	private String endTime;
	private long executionTime;

	public Api() {
	}

	public Api(String serviceName, String api, String startTime, String endTime, long executionTime) {
		super();
		this.serviceName = serviceName;
		this.api = api;
		this.startTime = startTime;
		this.endTime = endTime;
		this.executionTime = executionTime;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public String getApi() {
		return api;
	}

	public static class ApiBuilder {
		private String serviceName;
		private String api;
		private Date startTime;
		private Date endTime;

		public ApiBuilder serviceName(String serviceName) {
			this.serviceName = serviceName;
			return this;
		}

		public ApiBuilder api(String api) {
			this.api = api;
			return this;
		}

		public ApiBuilder srartTime(Date startTime) {
			this.startTime = startTime;
			return this;
		}

		public ApiBuilder endTime(Date endTime) {
			this.endTime = endTime;
			return this;
		}

		public Api build() {

			return new Api(serviceName, api, DATE_FORMAT.format(startTime), DATE_FORMAT.format(endTime), endTime.getTime() - startTime.getTime());
		}
	}

}
