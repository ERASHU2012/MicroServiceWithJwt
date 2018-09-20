package com.kinexcs.demo.common.dto;

public class ConfigPropertyGetResponse {

	private String propertyName;
	private String propertyValue;

	public ConfigPropertyGetResponse() {
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}