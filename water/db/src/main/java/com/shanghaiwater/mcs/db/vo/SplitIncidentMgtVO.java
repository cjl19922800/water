package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SplitIncidentMgtVO extends IncidentBasicVO{
	
	private static final long serialVersionUID = 4500687713013864063L;

	private String conName;
	private String contactValue;
	private String phone;
	private String mail;
	private String yb;
	private String splitComment;
	
}
