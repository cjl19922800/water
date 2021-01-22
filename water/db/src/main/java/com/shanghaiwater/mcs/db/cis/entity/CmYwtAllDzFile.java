package com.shanghaiwater.mcs.db.cis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

/**
 * <p>
 * 一网通对账请求总表
 * </p>
 *
 * @author zhuwx
 * 
 */
@Data
public class CmYwtAllDzFile implements Serializable {

	private static final long serialVersionUID = 1L;

	// 交易码
	private String tran_code;

	// 银行代号
	private String bank_no;

	// 操作员名称
	private String oper_id;

	// 收费日期
	private Date pay_date;

	// 交易总金额
	private BigDecimal f_total;

	// 交易总笔数
	private BigDecimal f_num;

	// 对账文本文件名
	private String filename;

	// 0 成功 1 汇总行与请求总金额/总笔数不一致 2 汇总行与明细汇总总金额/总笔数不一致
	private String status;

	// 创建日期
	private Date cre_dttm;

	public CmYwtAllDzFile() {
	}

	public CmYwtAllDzFile(String tran_code, String bank_no, String oper_id, Date pay_date, BigDecimal f_total,
			BigDecimal f_num, String filename, String status, Date cre_dttm) {
		this.tran_code = tran_code;
		this.bank_no = bank_no;
		this.oper_id = oper_id;
		this.pay_date = pay_date;
		this.f_total = f_total;
		this.f_num = f_num;
		this.filename = filename;
		this.status = status;
		this.cre_dttm = cre_dttm;
	}
}
