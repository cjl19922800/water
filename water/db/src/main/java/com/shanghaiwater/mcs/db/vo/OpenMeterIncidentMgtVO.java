package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OpenMeterIncidentMgtVO extends IncidentBasicVO {

	
	private static final long serialVersionUID = 657534535467575677L;
	
	private String email;
	private String houseCertNo;
	private String houseCertType;
	private String bdczl;
	private String bdcdbrq;
	private String contactNum;
	private String bookTime;
	
}
