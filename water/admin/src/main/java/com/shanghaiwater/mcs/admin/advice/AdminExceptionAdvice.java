package com.shanghaiwater.mcs.admin.advice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.fluent.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.shanghaiwater.mcs.admin.model.BasicResponse;
import com.shanghaiwater.mcs.common.exception.MCSException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AdminExceptionAdvice {

	public static final String DEFAULT_ERROR_VIEW = "views/error";

	@ExceptionHandler(MCSException.class)
	@ResponseBody
	public Object mcsException(HttpServletRequest req, MCSException ex,HttpServletResponse response) throws IOException {
		log.error(ex.getMessage(), ex);
		log.error(req.getRequestURL().toString());
		Object o = req.getAttribute("method_return_is_view");
		Boolean isView = (Boolean) o;
		if(isView) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("errorCode", ex.getErrorCode());
			mav.addObject("errorMessage", ex.getMessage());
			mav.setViewName(DEFAULT_ERROR_VIEW);
			return mav;
		}else {
			BasicResponse basicResponse = new BasicResponse();
			basicResponse.setCode(500);
			basicResponse.setErrCode(ex.getErrorCode());
			basicResponse.setErrMessage(ex.getMessage());
			basicResponse.setSuccess(false);
			return basicResponse;
		}
		
		
	}

	
	
}