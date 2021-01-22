package com.shanghaiwater.mcs.admin.enums;

public enum OperationEnums {

	QUERY("query","查询"),
	LOGIN("login","登录"),
	INSERT("insert","新增"),
	UPDATE("update","修改"),
	DELETE("delete","删除"),
	SET("set","设置"),
	AUDIT("auidt","审核"),
	APPLY("apply","申请"),
	;
	
	private OperationEnums(String code, String name) {
		this.code = code;
		this.name = name;
	}
	private String code;
	private String name;
	
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static String getOperationNameByCode(String code) {
		for (OperationEnums typeEnum : OperationEnums.values()) {
			if(typeEnum.getCode().equals(code)) {
				return typeEnum.getName();
			}
		}
		return code;
	}

}
