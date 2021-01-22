package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_MEMBER_RELATIONSHIP")
public class McsMemberShip extends BaseEntity{

	private static final long serialVersionUID = 5987123583565040223L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 户号
	 */
	private String userNo;
	/**
	 * 供水公司No号
	 */
	private String companyNo;
	/**
	 * 关联关系
	 */
	private String ship;
	/**
	 * 是否解绑
	 */
	private Boolean status = false;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 一网通办返回的Id
	 */
	private String iniUserId;
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
	 * 地址
	 */
	private String address;
	/**
	 * 业主
	 */
	private String ownerName;
	/**
	 * 业主证件号
	 */
	private String ownerIdNo;
	/**
	 * 别名
	 */
	private String alias;
	
	
}
