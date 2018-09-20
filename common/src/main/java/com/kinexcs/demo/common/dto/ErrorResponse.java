package com.kinexcs.demo.common.dto;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1507965949875951083L;
	private String errorMsg;
	private String exceptionClass;

	public String getErrorMsg() {
		return errorMsg;
	}

	
	public ErrorResponse() {
	}

	public ErrorResponse(Exception e) {
		this.errorMsg = e.getMessage();
		this.exceptionClass=e.getClass().getName();
	}


	public String getExceptionClass() {
		return exceptionClass;
	}

}
