package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_MEMBER_PWD_UPDATE")
public class McsMemberPwdUpdate extends BaseEntity{

	private static final long serialVersionUID = 1859043308911963179L;

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 创建人
	 */
	private String createId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 密码
	 */
	private String pwd;
	
}
