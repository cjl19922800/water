package com.shanghaiwater.mcs.admin.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsAulog;

import lombok.Data;

@Data
public class AulogPageResponse extends ResponseModel{
	
	private String count;

	private IPage<McsAulog> data;

	private Long total;

	private Boolean success;

}
