package com.shanghaiwater.mcs.admin.model;

import javax.validation.constraints.NotBlank;

import com.shanghaiwater.mcs.common.model.RequestModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PasswordRequest extends RequestModel{
	
	@NotBlank(message = "原密码不可为空")
	private String oldPassword;
	
	@NotBlank(message = "新密码不可为空")
	private String newPassword;
	
	@NotBlank(message = "id不可为空值")
	private String id;
}
