package com.shanghaiwater.mcs.db.cis.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * <p>
 * 一网通对账明细表
 * </p>
 *
 * @author zhuwx
 * 
 */
@Data
public class CmYwtAllDzFileDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	// 银行代码 修改为 支付类型
	private String bank_no;

	// 操作人
	private String oper_id;

	// 流水号 修改为 订单号
	private String flow_no;

	// 收费日期
	private Date pay_date;

	// 账户编号
	private String acct_id;

	// 收费金额
	private String pay_money;

	// 清算状态
	private String qs_status;

	// 对账状态(N对账未完成，Y对账完成)
	private String dz_flg;

	// 创建日期
	private Date cre_dttm;

	// 写入日期
	private Date insert_dt;

	// 提取日期
	private Date extract_dt;

	// 状态(00成功，99失败)
	private String status_flg;

	// 文件名
	private String filename;

	public CmYwtAllDzFileDetail() {
	}

	public CmYwtAllDzFileDetail(String bank_no, String oper_id, String flow_no, Date pay_date, String acct_id,
			String pay_money, String qs_status, String dz_flg, Date cre_dttm, Date insert_dt, Date extract_dt,
			String status_flg, String filename) {
		this.bank_no = bank_no;
		this.oper_id = oper_id;
		this.flow_no = flow_no;
		this.pay_date = pay_date;
		this.acct_id = acct_id;
		this.pay_money = pay_money;
		this.qs_status = qs_status;
		this.dz_flg = dz_flg;
		this.cre_dttm = cre_dttm;
		this.insert_dt = insert_dt;
		this.extract_dt = extract_dt;
		this.status_flg = status_flg;
		this.filename = filename;
	}
}
