package com.shanghaiwater.mcs.admin.service.inf;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.vo.YwtbUserInfo;
import com.shanghaiwater.mcs.mcsif.ywtb.YwtbClient;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetApplyNoRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetApplyNoResponse;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetTokenRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetTokenResponse;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetUserRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetUserResponse;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbProcessRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbProcessResponse;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbSaveRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbSaveResponse;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbSubmitRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbSubmitResponse;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbUpdateStatusRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbUpdateStatusResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 一网通办客户端Service
 */
@Slf4j
@Service
public class YwtbClientService {

	@Value("${ywtb.endpoint}")
	private String ywtbEndpoint;

	@Value("${ywtb.oauthEndpoint}")
	private String ywtbOauthEndpoint;

	@Value("${ywtb.version}")
	private String ywtbVersion;

	@Value("${ywtb.clientId}")
	private String ywtbClientId;

	@Value("${ywtb.clientSecret}")
	private String ywtbClientSecret;

	private YwtbClient ywtbClient;
	private String accessToken;
	private long expireTime;

	public YwtbClientService() {
		expireTime = 0;
	}

	public synchronized void getClientAccessToken() {
		if (ywtbClient == null) {
			ywtbClient = new YwtbClient(ywtbEndpoint, ywtbOauthEndpoint, ywtbVersion);
		}

		if (expireTime > new Date().getTime()) {
			return;
		}

		YwtbGetTokenRequest tokenRequest = new YwtbGetTokenRequest();
		tokenRequest.setClientId(ywtbClientId);
		tokenRequest.setClientSecret(ywtbClientSecret);
		YwtbGetTokenResponse tokenResponse = ywtbClient.getToken(tokenRequest);
		accessToken = tokenResponse.getAccess_token();

		Integer expires = Integer.valueOf(tokenResponse.getExpires_in());
		expires -= 600;
		if (expires < 600) {
			expires = 600;
		}

		expireTime = new Date().getTime();
		expireTime += expires * 1000;

		log.info("一网通办PortalToken={}", accessToken);
		log.info("PortalToken过期时间：{}", new Date(expireTime));
	}

	// 获取个人portal_token // 测试用 个人和企业均用该方法
	public String getUserPortalToken(String ywtbUserId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://117.184.226.149/uc/oauth2.0/authorize.do?zwdtsw_user_id=" + ywtbUserId;
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return responseEntity.getBody().toString();
	}

	public YwtbUserInfo getYwtbUserInfo(String portalToken, String itemType) {
		getClientAccessToken();

		YwtbGetUserRequest userRequest = new YwtbGetUserRequest();
		userRequest.setAccessToken(accessToken);
		userRequest.setPortalToken(portalToken);

		YwtbGetUserResponse ywtbGetUserResponse;
		ywtbGetUserResponse = ywtbClient.getUser(userRequest);
//		if ("P".equals(itemType)) { // 个人
//			ywtbGetUserResponse = GsonUtil.getInstance().fromJson(
//					"{\"isSuccess\":true,\"code\":\"200\",\"msg\":\"成功\",\"version\":\"0.1\",\"data\":{\"userId\":\"fcd92449-81f3-4383-b52f-035f23715482\",\"type\":\"个人\",\"username\":\"田彦\",\"idCardNo\":\"310110198310044437\",\"account\":\"eshimin94808358\",\"mobile\":\"13301981738\",\"email\":\"12345678@163\",\"address\":\"娄山关路168号\",\"zipCode\":\"200000\",\"status\":\"激活\",\"realNameStatus\":\"验证通过\",\"completeType\":\"\",\"companyName\":\"\",\"socialCreditCode\":\"\",\"level\":0,\"caCode\":\"\",\"certSn\":\"\",\"regTime\":\"2018-08-09\",\"updateTime\":\"2019-09-27\",\"userCacert\":\"中级\",\"permisssion\":\"\",\"certificate\":\"\",\"commerceCode\":\"\",\"certType\":\"身份证\"}}",
//					YwtbGetUserResponse.class);
//		} else { // 企业
//			ywtbGetUserResponse = GsonUtil.getInstance().fromJson(
//					"{\"isSuccess\":true,\"code\":\"200\",\"msg\":\"成功\",\"version\":\"0.1\",\"data\":{\"userId\":\"86f81ee2-d7aa-44de-9a24-63d619ff4db4\",\"type\":\"法人\",\"username\":\"张国会\",\"idCardNo\":\"\",\"account\":\"\",\"mobile\":\"18621189566\",\"email\":\"626866507@qq.com\",\"address\":\"上海市奉贤区庄行镇钜庭路99弄香溢雅园218号201室\",\"zipCode\":\"201402\",\"status\":\"\",\"realNameStatus\":\"是\",\"completeType\":\"\",\"companyName\":\"上海臻欧实业发展有限公司\",\"socialCreditCode\":\"\",\"orgCode\":\"05296672-0\",\"level\":0,\"caCode\":\"2@GS310120002057211\",\"certSn\":\"5D1A93D8CC7164232BC06C17AEDC7151\",\"regTime\":\"2020-03-21 02:26:04\",\"updateTime\":\"2020-03-21 02:26:04\",\"userCacert\":\"\",\"permisssion\":\"\",\"certificate\":\"\",\"commerceCode\":\"310120002057211\",\"certType\":\"\"}}",
//					YwtbGetUserResponse.class);
//		}

		log.info(ywtbGetUserResponse.toString());

		return ywtbGetUserResponse.getData();
	}

	public String generateApplyNo(String itemCode) {
		getClientAccessToken();

		YwtbGetApplyNoRequest ywtbGetApplyNoRequest = new YwtbGetApplyNoRequest();
		ywtbGetApplyNoRequest.setAccessToken(this.accessToken);
		ywtbGetApplyNoRequest.setItemCode(itemCode);

		YwtbGetApplyNoResponse res = ywtbClient.generateApplyNo(ywtbGetApplyNoRequest);
		if (!"200".equals(res.getCode())) {
			throw new MCSException("ERR.GEN_APPLYNO", res.getMsg());
		}
		System.out.println("事项编码======"+itemCode);
		System.out.println("返回的申请单号========="+res.getData().getApplyNo());
		return res.getData().getApplyNo();
	}

	public YwtbSaveResponse saveApply(YwtbSaveRequest ywtbSaveRequest) {
		getClientAccessToken();

		ywtbSaveRequest.setAccessToken(this.accessToken);
		YwtbSaveResponse res = ywtbClient.saveApply(ywtbSaveRequest);
		if (!"200".equals(res.getCode())) {
			throw new MCSException("ERR.GEN_APPLYNO", res.getMsg());
		}

		return res;
	}
	
	public YwtbSubmitResponse submitApply(YwtbSubmitRequest ywtbSubmitRequest) {
		getClientAccessToken();
		ywtbSubmitRequest.setAccessToken(accessToken);
		YwtbSubmitResponse response = ywtbClient.submitApply(ywtbSubmitRequest);
		if (!"200".equals(response.getCode())) {
			throw new MCSException("ERR.GEN_APPLYNO", response.getMsg());
		}
		return response;
	} 
	
	public YwtbUpdateStatusResponse statusUpdate(YwtbUpdateStatusRequest request) {
		getClientAccessToken();
		request.setAccessToken(accessToken);
		YwtbUpdateStatusResponse response = ywtbClient.updateStatus(request);
		return response;
	}

	public YwtbProcessResponse process(YwtbProcessRequest ywtbProcessRequest) {
		getClientAccessToken();
		ywtbProcessRequest.setAccessToken(accessToken);
		YwtbProcessResponse response = ywtbClient.processApply(ywtbProcessRequest);
		return response;
	}
	
	
	
}
