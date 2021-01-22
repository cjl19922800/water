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
@TableName("usw_charge_collection")
public class UswChargeCollection extends BaseEntity {

	
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String source;

	private String applicant;
	
	private String certType;
	
	private String certNumber;
	
	private String companyName;
	
	private String companyType;
	
	private String nsrsbh;
	
	private String contactNum;
	
	private String email;
	
	private String fczjlx;
	
	private String fczjhm;
	
	private String businessType;
	
	private String company;   //水司
	
	private String cardId;
	
	private String bdczl;
	
	private String bdcdbrq;
	
	private String address;
	
	private String creator;

	private Date creatTime;

	private String updator;

	private Date updateTime;

	private String recordId;

	private String recordVersion;
	
	private String bankAccount;
	
	private String accountName;
	
	private String headBank;
	
	private String bankBank;
	
}
