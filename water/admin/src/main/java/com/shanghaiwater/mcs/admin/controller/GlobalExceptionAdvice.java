package com.shanghaiwater.mcs.admin.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.common.model.RequestModel2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@PermissionIntercept
public class GlobalExceptionAdvice {

	private String profile;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public RequestModel2 bindException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();

		StringBuffer errorMesssage = new StringBuffer();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMesssage.append(fieldError.getDefaultMessage() + ", ");
		}
		// 此处代码为项目内统一返回java类 这里不做该类展示
		RequestModel2 response = new RequestModel2();
		response.setErrCode("111");
		response.setErrMessage(errorMesssage.toString());

		return response;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RequestModel2 exception(Exception ex) {
		log.error("", ex);
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));

		RequestModel2 response = new RequestModel2();
		response.setErrCode("111");
		response.setErrMessage(stringWriter.toString());

		return response;
	}

}