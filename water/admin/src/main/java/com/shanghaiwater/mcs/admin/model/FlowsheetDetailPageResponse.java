package com.shanghaiwater.mcs.admin.model;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.AccFlowsheetDetail;

import lombok.Data;

@Data
public class FlowsheetDetailPageResponse extends ResponseModel{
	
	private String count;

	private IPage<AccFlowsheetDetail> data;

	private Long total;

	private Boolean success;

}
