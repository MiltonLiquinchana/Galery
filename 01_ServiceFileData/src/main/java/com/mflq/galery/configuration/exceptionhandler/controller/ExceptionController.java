package com.mflq.galery.configuration.exceptionhandler.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mflq.galery.configuration.exceptionhandler.exceptions.CustomNotFoundException;
import com.mflq.galery.configuration.exceptionhandler.exceptions.ExceptionResponse;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {
	@Autowired
	private ErrorAttributes errorAttributes;

	Map<String, Object> mapErrors;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request)
			throws Exception {
		mapErrors = errorAttributes.getErrorAttributes(request,
				ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), mapErrors.get("trace").toString());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(CustomNotFoundException ex, WebRequest request)
			throws Exception {

		mapErrors = errorAttributes.getErrorAttributes(request,
				ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), mapErrors.get("trace").toString());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
