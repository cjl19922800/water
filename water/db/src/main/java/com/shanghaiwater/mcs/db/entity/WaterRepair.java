package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("rpr_usewater_repair")
public class WaterRepair extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	@TableField("fa_id")

	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String caseTypeCd;

	private String cardId;

	private String acctId;

	private String perName;

	private String contactValue;

	@TableField("fyly")
	private String fyly;

	@TableField("fyxs")
	private String fyxs;

	@TableField("fyqm")
	private String fyqm;

	@TableField("fylb")
	private String fylb;

	@TableField("fynr")
	private String fynr;

	private Date bxDttm;

	@TableField("cljb")
	private String cljb;

	private Date cnDttm;

	private String bxLocCd;

	@TableField("comment")
	private String comment;

	@TableField("address")
	private String address;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

}
