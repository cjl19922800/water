package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ShareMeterIncidentMgtVO extends IncidentBasicVO {

	private static final long serialVersionUID = 903328392480331231L;
	private String contactNum;
	private String email;
	private String reqType;
	private String bookletNum;
	private String houseCertNo;
	private String houseCertType;
	private String appType;
}
