package com.shanghaiwater.mcs.db.cis.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * <p>
 * 回盘中间库表
 * </p>
 *
 * @author zhuwx
 * 
 */
@Data
public class CmYwtHpFileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String bank_no;// 银行代码 修改为 支付类型
	private String oper_id;// 操作人
	private String flow_no;// 流水号 修改为 订单号
	private String pay_date;// 收费日期
	private String acct_id;// 账户编号
	private String pay_money;// 收费金额
	private Date cre_dttm;// 创建日期
	private Date up_dttm;// 处理日期
	private String status_flg;// 处理状态(0 未完成，1完成)
	private String qs_status;// 清算状态
	private String hp_status;// 回盘状态(01 取消订单，02 取消付款)

	public CmYwtHpFileDetail() {
	}

	public CmYwtHpFileDetail(String bank_no, String oper_id, String flow_no, String pay_date, String acct_id,
			String pay_money, Date cre_dttm, Date up_dttm, String status_flg, String qs_status, String hp_status) {
		this.bank_no = bank_no;
		this.oper_id = oper_id;
		this.flow_no = flow_no;
		this.pay_date = pay_date;
		this.acct_id = acct_id;
		this.pay_money = pay_money;
		this.cre_dttm = cre_dttm;
		this.up_dttm = up_dttm;
		this.status_flg = status_flg;
		this.qs_status = qs_status;
		this.hp_status = hp_status;
	}
}
