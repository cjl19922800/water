package com.shanghaiwater.mcs.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("PasswordPolicy")
public class PasswordPolicy extends BaseEntity{

	private String passId;
	
	private String content;
	
	private String passLength;
	
	private String  failure;
	
	private String repeat;
	
	private String lock;

	public String getPassId() {
		return passId;
	}

	public void setPassId(String passId) {
		this.passId = passId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPassLength() {
		return passLength;
	}

	public void setPassLength(String passLength) {
		this.passLength = passLength;
	}

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}
	
	
}
