package com.kinexcs.demo.common.dto;

public class ConfigPropertyGetRequest {

	private String propertyName;

	public ConfigPropertyGetRequest() {
	}

	public ConfigPropertyGetRequest(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
