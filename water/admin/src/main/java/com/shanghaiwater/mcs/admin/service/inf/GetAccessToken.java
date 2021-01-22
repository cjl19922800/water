package com.shanghaiwater.mcs.admin.service.inf;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shanghaiwater.mcs.mcsif.ywtb.YwtbClient;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetTokenRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetTokenResponse;

@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class GetAccessToken {

	private static String access_token;

	private static GetAccessToken instance = new GetAccessToken();

	private GetAccessToken() {
	}

	public static GetAccessToken getInstance() {
		return instance;
	}

	// 3.添加定时任务
	@Scheduled(cron = "0/1500 * * * * ?")
	private void configureTasks() throws Exception {
		/*
		 * YwtbClient client = new YwtbClient("", "", null); YwtbGetTokenRequest
		 * tokenRequest = new YwtbGetTokenRequest(); tokenRequest.setClientId("admin");
		 * tokenRequest.setClientSecret("admin"); YwtbGetTokenResponse tokenResponse =
		 * client.getToken(tokenRequest); GetAccessToken.access_token =
		 * tokenResponse.getAccess_token(); System.out.println(access_token);
		 */
	}

	public String getAccess_token() {
		return access_token;
	}

}
