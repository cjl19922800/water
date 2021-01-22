package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MCS
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_user")
public class SysAdminUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String userId;

	private String userName;

	@NotEmpty(message = "密码不可为空")
	private String userPassword;

	private String displayName;

	private String email;

	private String cellPhone;

	private Date lastPasswordLogin;

	private String isRootAdmin;

	private String isShwGroupAdmin;

	private String description;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

	private String roleId;
	
	private String status;
}
