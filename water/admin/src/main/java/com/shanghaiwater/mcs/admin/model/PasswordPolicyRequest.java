package com.shanghaiwater.mcs.admin.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.shanghaiwater.mcs.admin.common.RequestModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PasswordPolicyRequest extends RequestModel {
	@NotEmpty(message = "请确认输入的参数")
	@Length(min = 1, max = 1, message = "只能输入0或1")
	private String hasNum;

	@NotEmpty(message = "请确认输入的参数")
	@Size(min = 1, max = 1, message = "只能输入0或1")
	private String hasLowerChar;

	@NotEmpty(message = "请确认输入的参数")
	@Length(min = 1, max = 1, message = "只能输入0或1")
	private String hasUpperChar;

	@NotEmpty(message = "请确认输入的参数")
	@Length(min = 1, max = 1, message = "只能输入0或1")
	private String hasSpecialChar;

	@NotNull(message = "请确认输入的参数")
	@Min(value = 8,message = "最小值是8")
	@Max(value = 32,message = "最大值是32")
	private Integer minLength;

	@NotNull(message = "请确认输入的参数")
	@Min(value = 0,message = "最小值是0")
	@Max(value = 365,message = "最大值是365")
	private Integer validPeriod;

	@NotNull(message = "请确认输入的参数")
	@Min(value = 1,message = "最小值是1")
	@Max(value = 99,message = "最大值是99")
	private Integer retrynumber;
	
	
	private String id;

}
