package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_MEMBER_LOG")
public class McsMemberLog extends BaseEntity{

	private static final long serialVersionUID = 5987123583565040223L;
	
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 操作类型
	 */
	private String operationType;
	/**
	 * 操作名称
	 */
	private String operationName;
	/**
	 * 操作描述
	 */
	private String msg;
	/**
	 * 来源
	 */
	private String source;
}
