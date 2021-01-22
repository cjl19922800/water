package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CusChangeIncidentMgtVO extends IncidentBasicVO {

	
	private static final long serialVersionUID = 6575534524352341456L;
	private String mailAddress;
	private String postCode;
}
