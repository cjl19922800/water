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
@TableName("mcs_diff_duizhang_detail")
public class AccDiffDuizhangDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String diffWxtDetailId;

	private String diffWxtId;

	// 共通字段
	private String bankNo;// 支付机构代号 固定值T // CIS: 银行代码 修改为 支付类型
	private String operId;// 操作员名称
	private String flowNo;// 流水号 // CIS流水号 修改为 订单号

	// WLY字段
	private String fqfs;// 发起方式 固定值：3 char
	private String sfrq;// 收费日期 yyyymmdd char
	private String userNo;// 销根号 char
	private String sZhangdantxm;// 账单条码 char
	private String money;
	private String nWeiyuej;// 违约金 必填，格式0.00 char
	private String zhifulx;// 支付类型 必填00：支付宝01：微信02：银联..... char
	private String feeid;// 欠费记录号 char
	private String orderId;// 订单号
	private String status;// 状态

	// CIS字段
	private String payDate;// 收费日期
	private String acctId;// 账户编号
	private String payMoney;// 收费金额
	private Date creDttm;// 创建日期
	private Date upDttm;// 处理日期
	private String statusFlg;// 处理状态(0 未完成,1完成)
	private String qsStatus;// 清算状态
	private String hpStatus;// 回盘状态(01 取消订单,02 取消付款)

	// MCS状态字段
	private String isRepeat;// 判断此订单是否重复(0 不重复,1 重复)
	private String isMiddle;// 标识符，判断是否是中间状态数据
	private String orderStatus;// 订单状态
	private String mcsStatus;// 订单状态

	// 全局共通字段
	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;
}
