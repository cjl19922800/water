package com.shanghaiwater.mcs.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@MapperScan("com.shanghaiwater.mcs.db.mapper")
public class McsServiceDbApplicationTests {

	@Test
	public void contextLoads() {
	}

}
