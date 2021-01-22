package com.shanghaiwater.mcs.db.vo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ResidentApplyMgtVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String applicant;

	private Date appdate;

	private String status;

	private String incidentType;

	private String certType;

	private String certNumber;

	private String email;

	private String companyName;

	private String companyType;

	private String socialCreditCode;

	private String description;

	private String applyNo;

	private String town;

	private String xzq;

	/**
	 * Apply
	 */
	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String businessType;

	private String perName;

	private String xzqApply;

	private String address;

	private String applyContent;

	private String conName;

	private String phone;

	private String mail;

	private String wygz;

	private String contactAddress;

}
