package com.shanghaiwater.mcs.admin.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
public class ExceptionPreHandleInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        // 判断返回值是否为字符串
        boolean isString = method.getReturnType().equals(String.class);
        // 判断方法是否使用 @ResponseBody
        boolean isResonseBody = !method.isAnnotationPresent(ResponseBody.class);
        // 判断方法是否使用 @RestController注解
        boolean isRestController = !hm.getBeanType().isAnnotationPresent(RestController.class);
        request.setAttribute("method_return_is_view",isString && isResonseBody && isRestController);
        return true;
    }
	
}
