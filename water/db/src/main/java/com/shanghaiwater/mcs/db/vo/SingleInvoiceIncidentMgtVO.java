package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SingleInvoiceIncidentMgtVO extends IncidentBasicVO{

	private static final long serialVersionUID = 5664837842433899998L;
	
	private String contactValue;
	private String email;
	private String billAmount;
	private String companyName;
	private String registerAddress;
	private String account;
	private String bank;
	private String bankCard;
}
