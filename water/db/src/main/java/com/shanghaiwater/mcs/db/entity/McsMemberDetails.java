package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("MCS_MEMBER_DETAILS")
public class McsMemberDetails extends BaseEntity{
	
	private static final long serialVersionUID = -2598548608752019469L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String userId;//用户ID
	
	private String createId;//创建人
	
	private String updateId;//修改人
	
	private String type;//类型（公司和个人）
	
	private String idCardNo;//身份证
	
	private String idCardType;//证件类型
	
	private String userName;//姓名
	
	private String account;//昵称
	
	private String email;//邮箱
	
	private String address;//地址
	
	private String zipCode;//邮编
	
	private String companyType;//公司类型
	
	private String companyName;//公司名称
	
	private String socialCreditCode;//统一社会信用代码
	
	private String orgCode;//组织机构代码
	
	private Date createTime;
	
	private Date updateTime;
	
	private String sex;
	
	private String source;
}
