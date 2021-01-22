package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApiIncidentVo extends BaseVO{
	
	private static final long serialVersionUID = 2133124449308579358L;

	private String incidentId;
	
	private String userNo;
	
	private String incidentType;
	
	private String applyNo;
	
	private String appdate;
	
	private String status;
	
	private String alias;
	
}
