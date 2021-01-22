package com.shanghaiwater.mcs.admin.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RealNameMgtVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private String incidentId;

	private String applicant;

	private Date appdate;

	private String status;

	private String incidentType;

	private String certType;

	private String certNumber;
	
	private String shwCompany;

	private String email;

	private String companyName;

	private String companyType;

	private String socialCreditCode;

	private String description;

	private String applyNo;

	private String town;

	private String xzq;

	
	

	private String faId; // 工单编号

	private String cmSta; // 当前状态

	private Date updateDttm; // 更新时间

	private String businessType; // 业务类型

	private String cardId; // 销根号

	private String acctId; // 客户编号


	private String address; // 用水地址

	private String propertyOwner; // 产权人

	private String proOwnerPhone; // 产权人联系电话


	private String userCardType; // 身份证类型

	private String userCardId; // 身份证号

	private String houseCertType; // 房产证类型

	private String houseCertNo; // 房产证号

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordId;

	private String recordVersion;

	private String applyPhone; // 申请人联系方式
	
	

	
}
