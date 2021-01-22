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
@TableName("usw_share_meter")
public class UswShareMeter extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;
	
	private String source;
	
	private String applicant;
	
	private String appType;
	
	private String certType;
	
	private String certNumber;
	
	private String contactNum;
	
	private String email;
	
	private String reqType;
	
	private String businessType;
	
	private String company;
	
	private String address;
	
	private Integer bookletNum;
	
	private String houseCertNo;
	
	private String houseCertType;
	
	private String creator;

	private Date creatTime;

	private String updator;

	private Date updateTime;

	private String recordId;

	private String recordVersion;
	
}
