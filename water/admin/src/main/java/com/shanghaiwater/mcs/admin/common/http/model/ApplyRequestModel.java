package com.shanghaiwater.mcs.admin.common.http.model;

public class ApplyRequestModel {

	private String accessToken;
	private String applyNo;
	private String itemCode;
	private String taskHandleItem;
	private String itemName;
	private String targetType;
	private String targetName;
	private String targetNo;
	private String userId;
	private String username;
	private String licenseNo;
	private String licenseType;
	private String JsgcApplyNo;
	private String JsgcTempApplyNo;
	private String JsgcCountryApplyNo;
	private String mobile;
	private String departCode;
	private String departName;
	private String source;
	private String content;
	private String opTime;
	private String submitType;
	private String info;
	private String ZipCode;
	private String Address;
	private String ProjectType;
	private String stOther1;
	private String stOther2;
	
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getTaskHandleItem() {
		return taskHandleItem;
	}
	public void setTaskHandleItem(String taskHandleItem) {
		this.taskHandleItem = taskHandleItem;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getTargetNo() {
		return targetNo;
	}
	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getJsgcApplyNo() {
		return JsgcApplyNo;
	}
	public void setJsgcApplyNo(String jsgcApplyNo) {
		JsgcApplyNo = jsgcApplyNo;
	}
	public String getJsgcTempApplyNo() {
		return JsgcTempApplyNo;
	}
	public void setJsgcTempApplyNo(String jsgcTempApplyNo) {
		JsgcTempApplyNo = jsgcTempApplyNo;
	}
	public String getJsgcCountryApplyNo() {
		return JsgcCountryApplyNo;
	}
	public void setJsgcCountryApplyNo(String jsgcCountryApplyNo) {
		JsgcCountryApplyNo = jsgcCountryApplyNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getProjectType() {
		return ProjectType;
	}
	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}
	public String getStOther1() {
		return stOther1;
	}
	public void setStOther1(String stOther1) {
		this.stOther1 = stOther1;
	}
	public String getStOther2() {
		return stOther2;
	}
	public void setStOther2(String stOther2) {
		this.stOther2 = stOther2;
	}
	@Override
	public String toString() {
		return "ApplyRequestModel [accessToken=" + accessToken + ", applyNo=" + applyNo + ", itemCode=" + itemCode
				+ ", taskHandleItem=" + taskHandleItem + ", itemName=" + itemName + ", targetType=" + targetType
				+ ", targetName=" + targetName + ", targetNo=" + targetNo + ", userId=" + userId + ", username="
				+ username + ", licenseNo=" + licenseNo + ", JsgcApplyNo=" + JsgcApplyNo + ", JsgcTempApplyNo="
				+ JsgcTempApplyNo + ", JsgcCountryApplyNo=" + JsgcCountryApplyNo + ", mobile=" + mobile
				+ ", departCode=" + departCode + ", departName=" + departName + ", source=" + source + ", content="
				+ content + ", opTime=" + opTime + ", submitType=" + submitType + ", info=" + info + ", ZipCode="
				+ ZipCode + ", Address=" + Address + ", ProjectType=" + ProjectType + ", stOther1=" + stOther1
				+ ", stOther2=" + stOther2 + "]";
	}
	
	

}
