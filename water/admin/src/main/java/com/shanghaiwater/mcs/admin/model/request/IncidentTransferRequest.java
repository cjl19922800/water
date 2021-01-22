package com.shanghaiwater.mcs.admin.model.request;

import lombok.Data;

@Data
public class IncidentTransferRequest {

	private String incidentId;//申请单唯一编号
	
	private String companyCode;//公司
	
	private String description;//描述
	
}
