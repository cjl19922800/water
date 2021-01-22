package com.shanghaiwater.mcs.db.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IncidentMgtVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private String incidentId;

	private String applicant;

	private Date appdate;

	private String status;

	private String incidentType;
	private String incidentTypeLabel;

	private String shwCompany;
	private String shwCompanyLabel;

	private String shwAddress;

	private String certType;

	private String certNumber;

	private String email;

	private String companyName;

	private String companyType;

	private String socialCreditCode;

	private String applyNo;

	private String town;

	private String xzq;
	private String xzqLabel;

	private String description;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;
}
