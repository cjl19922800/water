package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsAulog;

import lombok.Data;

@Data
public class AulogResponse extends ResponseModel{
	
	private String count;

	private McsAulog data;

	private Integer total;

	private Boolean success;
	
	

}
