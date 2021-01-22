package com.shanghaiwater.mcs.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_BILL_INVOICE_LIST")
public class McsBillInvoiceList extends BaseEntity{

private static final long serialVersionUID = 1L;
	
	private String incidentId;
	
	private String billMonth;    //账单年月
	
	private String billMoney;
}
