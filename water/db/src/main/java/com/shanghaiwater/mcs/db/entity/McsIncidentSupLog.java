package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_INCIDENT_SUP_LOG")
public class McsIncidentSupLog extends BaseEntity{
	
	private static final long serialVersionUID = 3945278531806452658L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	
	private int sortNum;
	
	private String errCode;
	
	private String incidentType;
	
	private String errMsg;
	
	private boolean success = false;
	
	private String applyNo;
	
	private String userNo;
	
	private String company;
	
	private Date createTime;
	
}
