package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccountCancelIncidentMgtVO extends IncidentBasicVO {

	
	private static final long serialVersionUID = 686864636366666666L;
	
	private String companyType;
	private String companyName;
	private String email;
	private String fczjlx;
	private String fczjhm;
	private String contactNum;
	private String reason;
	private String bdczl;
	private String bdcdbrq;
}
