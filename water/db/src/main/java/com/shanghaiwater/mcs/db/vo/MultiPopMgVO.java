package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MultiPopMgVO extends IncidentBasicVO{

	private static final long serialVersionUID = -8994977804585220701L;
	private String peopleNum;
	private String reqType;
	private String phone;
	private String applyAddress;
	private String addressCheck;
	private String operType;
	private String settleMethod;
	
	
}
