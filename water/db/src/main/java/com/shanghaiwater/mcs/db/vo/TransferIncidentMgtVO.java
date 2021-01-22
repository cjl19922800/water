package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TransferIncidentMgtVO extends IncidentBasicVO{
	
	private static final long serialVersionUID = 8138556423053234986L;

	private String fczjlx;
	private String fczjhm;
	private String hourseCertDict;
	private String transferComment;
	private String contactValue;
	private String applyCompanyName;
	private String bdczl;
	private String bdcdbrq;
	
	
	
}
