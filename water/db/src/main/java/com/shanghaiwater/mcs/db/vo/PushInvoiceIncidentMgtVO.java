package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PushInvoiceIncidentMgtVO extends IncidentBasicVO {

	private static final long serialVersionUID = 5664837842433899998L;
	
	private String contactValue;
	
	private String applyType;
	
	private String bank;
	
	private String bankCard;
	
	private String email;
	
	private String registeAddress;
	
}
