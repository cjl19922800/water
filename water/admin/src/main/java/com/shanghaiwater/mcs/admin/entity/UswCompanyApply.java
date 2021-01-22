package com.shanghaiwater.mcs.admin.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MCS
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("usw_company_apply")
public class UswCompanyApply extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String businessType;

	private String perName;

	private String xzq;

	private String jdz;

	private String address;

	private String applyContent;

	private String project;

	private String contactPerson;

	private String phone;

	private String mail;

	private String ydxz;

	private String overallFloorage;

	private String buildType;

	private String wygz;

	private String khxz;

	private String sqlb;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

	private BigDecimal ysxqsc;

	private BigDecimal ysxqxf;

	private String frxm;

	private String frsfzhm;

	private String qyzjlx;

	private String qyzjbm;

}
