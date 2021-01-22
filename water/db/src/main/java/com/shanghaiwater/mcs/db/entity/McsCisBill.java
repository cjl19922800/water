package com.shanghaiwater.mcs.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mcs_cis_bill")
public class McsCisBill extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String incidentId;
	private String billId;
	private String billDate;
	private String money;
	private String invoiceStatus;
	private String invoiceComment;
}
