package com.shanghaiwater.mcs.db.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ChargeCollectionIncidentMgtVO extends IncidentBasicVO {

	
	private static final long serialVersionUID = 234565756576767876L;
	private String contactNum;
	private String email;
	private String fczjlx;
	private String fczjhm;
	private String nsrsbh;
	private String bdczl;
	private String bdcdbrq;
}
