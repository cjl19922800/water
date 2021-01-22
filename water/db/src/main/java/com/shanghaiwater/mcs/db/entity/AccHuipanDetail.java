package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mcs_huipan_detail")
public class AccHuipanDetail {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String detailId;
	private String counterofferId;
	private String companyCode; // 执收单位编码
	private String companyName; // 执收单位名称
	private String orderNo; // 订单号
	private String zffs; // 支付方式
	private String transactiondate; // 交易日期
	private String money; // 缴款金额
	private String refundMoney; // 退款金额
	private String balance; // 余额
	private String transactionType; // 交易类型
	private String finishTime; // 支付完成时间
	private String flowNo; // 渠道流水号
	private String moneyFlow; // 资金流向
	private String contributorName; // 缴款人名称
	private String status; // 清算状态
	private String field1; // 备用字段1
	private String field2; // 备用字段2
	private String field3; // 备用字段3
	// 如果返回 03 04 05 08 09 10 14 16 就正常上传到业务系统
	// 如果是其他返回码 就只保存下来 不上传
	private String upstatus; // 上传业务系统 00 否则 01

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

	private String orderStatus;// 订单状态
}
