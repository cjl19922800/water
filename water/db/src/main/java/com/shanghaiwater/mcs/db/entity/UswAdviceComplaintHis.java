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
@TableName("usw_advice_complaint_his")
public class UswAdviceComplaintHis extends BaseEntity {

	
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String uswAdviceComplaintHisId;
	
	private String incidentId;
	
	private String customType;
	
	private String shwAddress;
	
	private String applicant;
	
	private String incidentType;
	
	private String certType;
	
	private String certNumber;
	
	private String companyName;
	
	private String companyType;
	
	private String socialCreditCode;
	
	private String caseTypeCd;
	
	private String address;
	
	private String contactValue;
	
	private String fyly;           //反应来源
	
	private String fyxs;
	
	private String fyqm;
	
	private String fylb;
	
	private String fynr;
	
	private Date fyDttm;
	
	private String email;
	
	private String businessType;
	
	private String remark;
	
	private String creator;

	private Date creatTime;

	private String updator;

	private Date updateTime;

	private String recordId;

	private String recordVersion;
}
