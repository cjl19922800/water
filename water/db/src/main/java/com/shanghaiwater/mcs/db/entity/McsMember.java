package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_MEMBER")
public class McsMember extends BaseEntity{

	private static final long serialVersionUID = 5987123583565040223L;
	
	//type常量
	private static String[] QIYETYPE = {"C","企业"};
	
	private static String[] GERENTYPE = {"P","个人"};
	
	private static String[] CODESIGN = {"VERIFICATION","验证码注册"};
	
	private static String[] PWDSIGN = {"PWD","密码注册"};
	
	/**
	 * 用户ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String userId;
	/**
	 * 用户类型
	 */
	private String type;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 证件号
	 */
	private String idCardNo;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 创建人
	 */
	private String createId;
	/**
	 * 修改人
	 */
	private String updateId;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**
	 * 昵称
	 */
	private String account;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * email
	 */
	private String mail;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 是否激活
	 */
	private Boolean status = true;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 用户密码
	 */
	private String pwd;
	/**
	 * 用户来源
	 */
	private String source;
	
	/**
	 * 注册类型
	 */
	private String signStatus;
	/**
	 * 是否注销
	 */
	@TableField("IS_DELETE")
	private Boolean deleteStatus;
	
	private Date expiryTime;
	
	private String days;
	
	private Date lateyDate;//上一次登陆时间
	
	private Date currentDate;//本次登陆时间
	
	public static String[] getQIYETYPE() {
		return QIYETYPE;
	}
	public static void setQIYETYPE(String[] qIYETYPE) {
		QIYETYPE = qIYETYPE;
	}
	public static String[] getGERENTYPE() {
		return GERENTYPE;
	}
	public static void setGERENTYPE(String[] gERENTYPE) {
		GERENTYPE = gERENTYPE;
	}
	public static String[] getCODESIGN() {
		return CODESIGN;
	}
	public static void setCODESIGN(String[] cODESIGN) {
		CODESIGN = cODESIGN;
	}
	public static String[] getPWDSIGN() {
		return PWDSIGN;
	}
	public static void setPWDSIGN(String[] pWDSIGN) {
		PWDSIGN = pWDSIGN;
	}
	
}
