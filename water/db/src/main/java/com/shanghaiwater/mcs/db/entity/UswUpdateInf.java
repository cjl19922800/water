package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @author Will
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("usw_update_inf")
public class UswUpdateInf extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String businessType;
	
	private String cardId;
	
	private String acctId;
	
	private String applicant;
	
	private String waterAddress;
	
	private String mailAddress;
	
	private String postCode;
	
	private String contact1;
	
	private String contactNumber1;
	
	private String contactAddress1;
	
	private String contact2;
	
	private String contactNumber2;
	
	private String contactAddress2;
	
	private Date applyDttm;
	
	private String remark;
	
	private String creator;
	
	private Date cdate;
	
	private String updator;
	
	private Date udate;
	
	private String recordId;
	
	private String recordVersion;
	
	private String reciver;//收件人
	
}
