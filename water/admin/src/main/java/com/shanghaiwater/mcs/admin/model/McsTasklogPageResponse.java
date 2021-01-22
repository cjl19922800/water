package com.shanghaiwater.mcs.admin.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsTasklog;

import lombok.Data;

@Data
public class McsTasklogPageResponse extends ResponseModel{

	private String count;

	private IPage<McsTasklog> data;

	private Long total;

	private Boolean success;
}
