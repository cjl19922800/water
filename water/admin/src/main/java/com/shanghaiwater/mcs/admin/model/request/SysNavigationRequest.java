package com.shanghaiwater.mcs.admin.model.request;

import lombok.Data;

@Data
public class SysNavigationRequest {
	
	private String id;
	private String name;
	private Integer sortNo;
	private String explain;
	private String parentId;
	
}
