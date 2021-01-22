package com.shanghaiwater.mcs.admin.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shanghaiwater.mcs.admin.common.exception.handle.ExceptionHandle;
import com.shanghaiwater.mcs.admin.constants.Result;


@Aspect
@Component
//@PropertySource(value = {"classpath:exception.yml"})
public class HttpAspect {

	private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);
	
//	@Value("${exception.aop.scan}")
//	private String scan;
	
    @Autowired
    private ExceptionHandle exceptionHandle;
	
	@Pointcut("execution(public * com.shanghaiwater.mcs.userwater.controller.*.*(..))")
	public void log() {
		
	}
	
	//记录了调用的接口URL，调用的方法，调用方的IP地址以及输入的参数等
	@Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        LOGGER.info("url={}",request.getRequestURL());
        //method
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("id={}",request.getRemoteAddr());
        //class_method
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
        //args[]
        LOGGER.info("args={}",joinPoint.getArgs());
    }
	
	//在整个接口代码运作期间，使用@Around来捕获异常信息
	@Around("log()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Result result = null;
		try {

		} catch (Exception e) {
			return exceptionHandle.exceptionGet(e);
		}
		if(result == null){
			return proceedingJoinPoint.proceed();
		}else {
			return result;
		}
	}
	 
	
	@AfterReturning(pointcut = "log()",returning = "object")//打印输出结果
	public void doAfterReturing(Object object){
		LOGGER.info("response={}",object.toString());
	}
}
