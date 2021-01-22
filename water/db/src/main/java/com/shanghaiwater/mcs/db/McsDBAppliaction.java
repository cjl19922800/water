package com.shanghaiwater.mcs.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shanghaiwater.mcs.db")
public class McsDBAppliaction {

	public static void main(String[] args) {
		SpringApplication.run(McsDBAppliaction.class, args);
	}
}
