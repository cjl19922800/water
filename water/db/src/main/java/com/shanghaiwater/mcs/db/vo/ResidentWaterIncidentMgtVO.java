package com.shanghaiwater.mcs.db.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ResidentWaterIncidentMgtVO  extends IncidentBasicVO{
	
	private static final long serialVersionUID = 2520098767987806995L;
	private String houeseCertNo;
	private String houeseCertType;
	private String houeseCertDict;
	private String applyComment;
	private String applyContent;
	private String applyContentDict;
	private String conName;
	private String phone;
	private String contactAddress;
	
	
	
}
