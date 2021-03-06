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
@TableName("mcs_bill")
public class AccBill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String billId;
	private String reCode;
	private String tranCode;
	private String bankNo;
	private String operId;
	private String fqfs;
	private String userNo;
	private String flowNo;
	private String rowcount;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;
}
