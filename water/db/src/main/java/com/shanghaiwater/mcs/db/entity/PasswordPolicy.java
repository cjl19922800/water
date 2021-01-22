package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class PasswordPolicy {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String passwordPolicyId;
	
	private String hasNumber;
	
	private String hasLowerChar;
	
	private String hasUpperChar;
	
	private String hasSpecialChar;
	
	private Integer minLength;
	
	private Integer maxLength;
	
	private Integer validPeriod;
	
	private Integer retryNumber;
	
	private Integer lockTime;
	
	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;
}
