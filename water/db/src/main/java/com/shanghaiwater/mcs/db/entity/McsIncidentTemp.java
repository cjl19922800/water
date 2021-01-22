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
@TableName("MCS_INCIDENT_TEMP_SAVE")
public class McsIncidentTemp implements Serializable{
	
	private static final long serialVersionUID = 2897994919631007846L;
	
	/**
	 * 申请单ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;
	
	/**
	 * 申请单类型
	 */
	private String incidentType;
	
	/**
	 * 创建人
	 */
	private String cdtor;
	
	/**
	 * 创建时间
	 */
	private Date cdate;
	
	/**
	 * json数据存储字段
	 */
	private String dataJson;
	
	/**
	 * 户号
	 */
	private String userNo;
	
}
