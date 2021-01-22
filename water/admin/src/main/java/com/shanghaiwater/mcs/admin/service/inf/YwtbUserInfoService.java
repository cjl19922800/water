package com.shanghaiwater.mcs.admin.service.inf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shanghaiwater.mcs.admin.common.UserInfo;
import com.shanghaiwater.mcs.admin.feign.YwtbUserInfoFeign;
import com.shanghaiwater.mcs.common.model.masterdata.UserInfoResponse;
import com.shanghaiwater.mcs.common.vo.YwtbUserInfo;

@Service
public class YwtbUserInfoService {

	@Autowired
	private YwtbUserInfoFeign ywtbUserInfoFeign;

	@RequestMapping(value = "/userInfo/save")
	public boolean saveUserInfo(@RequestBody UserInfo userInfo) {
		UserInfoResponse response = ywtbUserInfoFeign.saveUserInfo(userInfo);
		if (response.getIsSuccess()) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/userInfo/get")
	public YwtbUserInfo getUserInfo(String userId) {
		UserInfoResponse response = ywtbUserInfoFeign.getUserInfo(userId);
		YwtbUserInfo data = response.getYwtbUserInfo();
		return data;
	}

}
