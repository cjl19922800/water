package com.shanghaiwater.mcs.admin.enums;

public enum ModuleEnums {

	USER("USER","用户管理"),
	ROLE("ROLE","角色管理"),
	POWER("POWER","权限管理"),
	NAVIGATION("NAVIGATION","导航管理"),
	RESOURCES("RESOURCES","资源管理"),
//	PASSWORD("PASSWORD","密码"),
	;
	
	ModuleEnums(String code, String name) {
		this.code = code;
		this.name = name;
	}
	private final String code;
	private final String name;
	
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
