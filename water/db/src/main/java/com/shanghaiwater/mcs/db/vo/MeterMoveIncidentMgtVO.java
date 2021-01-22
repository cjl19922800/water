package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MeterMoveIncidentMgtVO extends IncidentBasicVO {

	
	private static final long serialVersionUID = 980986768798090921L;
	
	private String contactNumber;
	private String email;
	private String bookTime;
	private String houseCertType;
	private String houseCertNo;
}
