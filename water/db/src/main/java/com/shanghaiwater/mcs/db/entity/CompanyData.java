package com.shanghaiwater.mcs.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mcs_company_data")
public class CompanyData {

	@TableId(type = IdType.ID_WORKER_STR)
	private String companyDataId;

	private String companyCode;
	private String companyName;
	private String appid;
	private String submerno;
	private String appsecret;
	private String moneyflow;
	private String companyNo;
//	private String duizhangBatchNo;
//	private String duizhangCurrentTime;
//	private String huipanBatchNo;
//	private String huipanCurrentTime;
//	private String diffBatchNo;
//	private String diffCurrentTime;

	private String payCategory;
	private String checkCode;

}
