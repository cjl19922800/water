package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdviceComplainIncidentMgtVO extends IncidentBasicVO {

	
	private static final long serialVersionUID = 664322312878779222L;
	private String customType;
	private String socialCreditCode;
	private String contactValue;
	private String fyly;
	private String fyqm;
	private String fyxs;
	private String fylb;
	private String fynr;
	private String email;
	private String remark;
	
}
