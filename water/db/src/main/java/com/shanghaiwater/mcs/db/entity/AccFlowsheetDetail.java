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
@TableName("mcs_flowsheet_detail")
public class AccFlowsheetDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String flowsheetDetailId;

	private String flowsheetId;

	private String orderId;//订单号

	private String money;//缴费金额

	private String status;//状态

	private String sbh;

	private String cTime;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;
	
	private String daystring;//支付成功日期

	private String payChannel;//支付渠道
	
	private String orderStatus;//订单状态
}
