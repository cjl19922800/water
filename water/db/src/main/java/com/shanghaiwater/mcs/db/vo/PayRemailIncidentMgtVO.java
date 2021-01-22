package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayRemailIncidentMgtVO extends IncidentBasicVO {

	private static final long serialVersionUID = 5375764547658768435L;
	
	private String contactValue;
	
	private String addressee;
	
	private String addPhone;
	
	private String mailAddress;
	
	private String postCode;
	
	private String billDate;
	
	private String noticeType;
}
