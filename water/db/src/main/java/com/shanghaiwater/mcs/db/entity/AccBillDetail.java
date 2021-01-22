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
@TableName("mcs_bill_detail")
public class AccBillDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String billDetailId;

	private String billId;
	private String feeid;
	private String userName;
	private String userAddr;
	private String bookid;
	private String dept;
	private String year;
	private String month;
	private String billingdate;
	private String readdate;
	private String money;
	private String nWeiyuej;
	private String sZhangdantxm;
	private String iZhangwuny;
	private String iCc;
	private String iShoufei;
	private String iZhangdanzt;
	private String iJumin;
	private String fengefu;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;
}
