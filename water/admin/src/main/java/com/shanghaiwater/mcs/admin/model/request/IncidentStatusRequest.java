package com.shanghaiwater.mcs.admin.model.request;

import lombok.Data;

@Data
public class IncidentStatusRequest {

	private String incidentId;//申请单唯一编号
	
	private String status;//状态
	
	private String description;//描述
	
}
