package com.shanghaiwater.mcs.admin.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminLoginInfo {
	private Boolean isRootAdmin = true;

	private Boolean isSHWAdmin;

	private String id;

	private String userId;

	private String userName;

	private String userPassword;

	private String displayName;

	private String email;

	private String cellPhone;

	private Date lastPasswordLogin;

	private String description;

	private List<String> menuList;

	private String companyCode;
	
	private String roleId;
	
	private String status;
	
	private List<String> authorities = new ArrayList<String>();//资源权限

	private List<String> companyAuthorities = new ArrayList<String>();//公司权限
}
