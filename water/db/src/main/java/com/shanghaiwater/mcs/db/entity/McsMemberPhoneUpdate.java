package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_MEMBER_PHONE_UPDATE")
public class McsMemberPhoneUpdate extends BaseEntity{

	private static final long serialVersionUID = -9093997994436959413L;

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
	 * 电话
	 */
	private String mobile;
	
}
