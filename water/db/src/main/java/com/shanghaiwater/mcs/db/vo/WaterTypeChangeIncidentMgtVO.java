package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WaterTypeChangeIncidentMgtVO extends IncidentBasicVO {

	private static final long serialVersionUID = 111132342347756755L;
	
	private String nsrsbh;
	private String email;
	private String contactNum;
	private String waterType;
}
