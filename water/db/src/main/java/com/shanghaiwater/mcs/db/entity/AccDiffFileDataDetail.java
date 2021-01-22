package com.shanghaiwater.mcs.db.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("MCS_DIFF_FILEDATA_DETAIL")
public class AccDiffFileDataDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String diffFiledataDetailId;
	private String diffFiledataId;
	private String companyCode;

	private String bankNo; // 支付机构代号
	private String operId; // 操作员名称
	private String flowNo; // 流水号
	private String fqfs; // 发起方式
	private String sfrq; // 收费日期
	private String userNo; // 销根号
	private String sZhangdantxm; // 账单条码
	private String money; // 实收金额
	private String nWeiyuej; // 违约金
	private String zhifulx; // 支付类型
	private String feeid; // 欠费记录号
	private String orderId; // 订单号
	private String status; // 状态

	private String orderStatus; // 查询订单本地保存状态
	private String mcsStatus;
	private String message;// 判断此订单是否重复(0 不重复,1 重复)

	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;
}
