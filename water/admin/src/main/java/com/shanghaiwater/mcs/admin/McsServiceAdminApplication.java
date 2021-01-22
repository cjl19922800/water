package com.shanghaiwater.mcs.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.shanghaiwater.mcs.db.mapper")
@EnableFeignClients
public class McsServiceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsServiceAdminApplication.class, args);
	}


}
