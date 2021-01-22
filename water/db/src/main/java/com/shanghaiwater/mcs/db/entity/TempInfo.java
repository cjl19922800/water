package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TempInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String caseTypeCd;

	private String cardId;

	private String acctId;

	private String perName;

	private String contactValue;

	private String fyly;

	private String fyxs;

	private String fyqm;

	private String fylb;

	private String fynr;

	private Date bxDttm;

	private String cljb;

	private Date cnDttm;

	private String bxLocCd;

	private String comment;

	private String address;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

	private String relationship;
}
