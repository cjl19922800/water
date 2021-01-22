package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("mcs_oplog")
public class McsOplog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	private String eventid;
	private String operation;
	private String optarget;
	private String operator;
	private String requestIp;
	private String requestUri;
	private Date startTime;
	private Date endTime;
	private String status;
	private String result;
	private String field1;//以用作水司
	private String field2;//以用作户号
	private String field3;
	
	private String field4;
	private String field5;
	private String field6;//来源
	
	
	@TableField(exist = false)
	private String eventName;
	
}
