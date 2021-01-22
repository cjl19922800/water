package com.shanghaiwater.mcs.admin.model.request;

import lombok.Data;

@Data
public class IncidentDealRequest {

	private String incidentId;//申请单唯一编号
	
	private boolean dealStatus;//处理状态
	
}
