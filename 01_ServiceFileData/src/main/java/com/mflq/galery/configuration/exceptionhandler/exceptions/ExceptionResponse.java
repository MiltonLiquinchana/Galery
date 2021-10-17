package com.mflq.galery.configuration.exceptionhandler.exceptions;

import java.util.Date;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionResponse extends ResponseEntityExceptionHandler {

	private Date timestamp;
	private String message;
	private String trace;

	public ExceptionResponse(String message, String trace) {

		this.timestamp = new Date();
		this.message = message;
		this.trace = trace;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

}
