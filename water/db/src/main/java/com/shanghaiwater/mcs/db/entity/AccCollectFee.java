package com.shanghaiwater.mcs.db.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mcs_collectfees")
public class AccCollectFee implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String CollectfeesId;

	private String orderid;
	private String companyNo;
	private String bankNo;
	private String operId;
	private String fqfs;
	private String userNo;
	private String feeid;
	private String payMoney;
	private String sZhangdantxm;
	private String payDate;
	private String flowNo;
	private String nWeiyuej;

	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;

}
