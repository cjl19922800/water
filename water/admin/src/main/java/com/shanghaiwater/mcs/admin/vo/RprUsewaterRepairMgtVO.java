package com.shanghaiwater.mcs.admin.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RprUsewaterRepairMgtVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private String incidentId;

	private String applicant;

	private Date appdate;

	private String status;

	private String incidentType;

	private String certType;

	private String certNumber;

	private String email;

	private String companyName;

	private String companyType;

	private String socialCreditCode;

	private String description;

	private String applyNo;

	private String town;

	private String xzq;

	/**
	 * Apply
	 */
	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String caseTypeCd;

	private String cardId;

	private String acctId;

	private String perName;

	private String contactValue;

	private String fyly;

	private String fyxs;

	private String fyqm;

	private String fylb;

	private String fynr;

	private Date bxDttm;

	private String cljb;

	private Date cnDttm;

	private String bxLocCd;

	private String comment;

	private String address;

}
