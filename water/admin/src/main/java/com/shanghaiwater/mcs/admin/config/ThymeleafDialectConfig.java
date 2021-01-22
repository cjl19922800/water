package com.shanghaiwater.mcs.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shanghaiwater.mcs.admin.dialect.DictDialect;

@Configuration
public class ThymeleafDialectConfig {
	
	@Bean
    public DictDialect dictSelectDialect() {
        return new DictDialect();
    }
	
}
