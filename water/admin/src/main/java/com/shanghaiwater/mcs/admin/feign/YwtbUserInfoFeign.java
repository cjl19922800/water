package com.shanghaiwater.mcs.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shanghaiwater.mcs.admin.common.UserInfo;
import com.shanghaiwater.mcs.common.model.masterdata.UserInfoResponse;

@FeignClient(name = "ywtbuserinfo", url = "http://localhost:8020")
//@FeignClient(name = "relationship", url = "http://192.168.6.71:8020")
public interface YwtbUserInfoFeign {

	@RequestMapping(value = "/userInfo/save")
	public UserInfoResponse saveUserInfo(@RequestBody UserInfo userInfo);

	@RequestMapping(value = "/userInfo/get")
	public UserInfoResponse getUserInfo(@RequestParam("userId") String userId);
}
