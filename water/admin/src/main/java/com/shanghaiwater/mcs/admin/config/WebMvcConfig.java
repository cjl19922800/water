package com.shanghaiwater.mcs.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shanghaiwater.mcs.admin.intercept.McsOpLogInterceptor;
import com.shanghaiwater.mcs.admin.intercept.SecurityInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

   @Value("${filePath}")
   private String filePath;
   
	
   @Bean
   public HandlerInterceptor handlerInterceptorAdapter() {
       return new SecurityInterceptor();
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
//       registry.addInterceptor(mcsOpLogInterceptor);
       registry.addInterceptor(handlerInterceptorAdapter()).addPathPatterns("/**");
   }
   
   
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/fileLoads/**").addResourceLocations("file:"+filePath);
	}
	
}
