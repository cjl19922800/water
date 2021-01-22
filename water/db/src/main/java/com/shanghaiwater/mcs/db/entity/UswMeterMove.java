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
@TableName("usw_meter_move")
public class UswMeterMove extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;
	
	private String source;
	
	private String applicant;
	
	private String certType;
	
	private String certNumber;
	
	private String email;
	
	private String contactNumber;
	
	private String bookTime;
	
	private String businessType;
	
	private String address;
	
	private String creator;
	
	private Date creatTime;
	
	private String updator;
	
	private Date updateTime;
	
	private String recordId;
	
	private String recordVersion;
	
	private String houseCertNo;
	
	private String houseCertType;
}
