package com.shanghaiwater.mcs.admin.service.inf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanghaiwater.mcs.admin.feign.YwtbUserInfoFeign;
import com.shanghaiwater.mcs.common.model.masterdata.UserInfoResponse;
import com.shanghaiwater.mcs.common.vo.YwtbUserInfo;
import com.shanghaiwater.mcs.mcsif.mcs.McsClient;
import com.shanghaiwater.mcs.mcsif.ywtb.YwtbClient;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetUserRequest;
import com.shanghaiwater.mcs.mcsif.ywtb.models.YwtbGetUserResponse;

/**
 * 查询登录用户的信息 （当前测试获取不了登录用户的portal_token，所以写了两个方法，之后可以改成一个）
 */
@Service
public class GetUserInfoService {
	@Autowired
	private YwtbUserInfoFeign ywtbUserInfoFeign;

	// 个人
	public YwtbUserInfo getUserInfo(String portal_token, String access_token) {
		YwtbClient client = new YwtbClient("", "", null);
		YwtbGetUserRequest userRequest = new YwtbGetUserRequest();
		userRequest.setAccessToken(access_token);

		// 获取个人信息
		portal_token = client.getResidentPortalToken();
		userRequest.setPortalToken(portal_token);
		YwtbGetUserResponse userResponse = client.getUser(userRequest);
		YwtbUserInfo userInfo = null;
		if ("200".equals(userResponse.getCode())) {
			userInfo = userResponse.getData();
		}
		return userInfo;
	}

	// 企业
	public YwtbUserInfo getBusinessInfo(String portal_token, String access_token) {
		YwtbClient client = new YwtbClient("", "", null);
		YwtbGetUserRequest userRequest = new YwtbGetUserRequest();
		userRequest.setAccessToken(access_token);

		portal_token = client.getCompanyPortalToken();
		userRequest.setPortalToken(portal_token);
		YwtbGetUserResponse userResponse = client.getUser(userRequest);
		YwtbUserInfo userInfo = null;
		if ("200".equals(userResponse.getCode())) {
			userInfo = userResponse.getData();
		} else {
			userInfo = new YwtbUserInfo();
			userInfo.setUsername("111");
			userInfo.setIdCardNo("ww");
			userInfo.setMobile("111");
			userInfo.setEmail("111");
			userInfo.setType("法人");
			userInfo.setSocialCreditCode("111");
			userInfo.setCompanyName("11111");

		}
		return userInfo;
	}

	public UserInfoResponse saveUserInfo(String access_token) {
		YwtbClient client = new YwtbClient("", "", null);
		YwtbGetUserRequest userRequest = new YwtbGetUserRequest();
		userRequest.setAccessToken(access_token);

		String portal_token = client.getCompanyPortalToken();
		userRequest.setPortalToken(portal_token);
		YwtbGetUserResponse userResponse = client.getUser(userRequest);
		YwtbUserInfo ywtbUserInfo = null;

		if ("200".equals(userResponse.getCode())) {
			ywtbUserInfo = userResponse.getData();
		} else {
			ywtbUserInfo = new YwtbUserInfo();
			ywtbUserInfo.setUsername("111");
			ywtbUserInfo.setIdCardNo("ww");
			ywtbUserInfo.setMobile("111");
			ywtbUserInfo.setEmail("111");
			ywtbUserInfo.setType("法人");
			ywtbUserInfo.setSocialCreditCode("111");
			ywtbUserInfo.setCompanyName("11111");
		}
		McsClient mcsClient = new McsClient(null);
		UserInfoResponse response = mcsClient.saveUserInfo(ywtbUserInfo);
//		UserInfoResponse response = ywtbUserInfoFeign.saveUserInfo(userInfo );
		return response;
	}

	public YwtbUserInfo queryUserInfo(String userId) {
		UserInfoResponse response = ywtbUserInfoFeign.getUserInfo(userId);
		return response.getYwtbUserInfo();
	}

}
