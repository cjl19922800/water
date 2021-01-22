package com.shanghaiwater.mcs.db.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mcs_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String userUuid;

	private String userId;

	private String type;// 用户类型（个人、法人、法人代理）

	private String username;// 用户实名

	private String idCardNo;// 用户身份证号

	private String account;// 用户昵称

	private String mobile;

	private String email;

	private String address;

	private String zipCode;// 邮政编码

	private String status;// 是否激活（激活、未激活）

	private String realNameStatus;// 实名验证状态（是、否）

	private String completeType;// 是否补全（如果补全：Y；如果没有补全：N）

	private String companyName;// 企业名称（实名）

	private String socialCreditCode;// 统一社会信用代码

	private String orgCode;// 组织机构代码

	private String level_;// 用户代表企业的级别，0为法人、1、2、3分别为办事人员的级别，对应着不同的权限等级。

	private String caCode;// 一证通编码

	private String certSn;// 证书序列号

	private String certificate;// 法人一证通公钥证书

//	
//	private String permission;//用户对该企业的权限集合

	private String regTime;

	private String updateTime;

//	
//	private String usewaterAddress;

//	
//	private String xzq;
//	
//	
//	private String idType;
//	
//	
//	private String qylx;
//	

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;
}
