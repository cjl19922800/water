package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RealNameIncidentMgtVO extends IncidentBasicVO {

	private static final long serialVersionUID = 5623686613019860989L;
	private String propertyOwner;
	
	private String proOwnerPhone;
	
	private String userCardId;
	
	private String userCardType;
	
	private String houseCertType;
	
	private String houseCertNo;
	
	private String applyPhone;
	
	
}
