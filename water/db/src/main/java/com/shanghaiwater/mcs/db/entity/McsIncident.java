package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("mcs_incident")
public class McsIncident extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String applicant;

	private Date appdate;

	private String status;

	private String incidentType;

	private String shwCompany;

	private String shwAddress;

	private String certType;

	private String certNumber;

	private String email;

	private String companyName;

	private String companyType;

	private String socialCreditCode;

	private String description;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

	private String applyNo;

	private String town;

	private String xzq;
	
	/**
	 * 发往一网通办是否成功
	 */
	private boolean ywtbSuccess = false;
	/**
	 * 发往业务系统是否成功
	 */
	private boolean ywxtSuccess = false;
	/**
	 * 销根号
	 */
	private String userNo;
	/**
	 * 来源
	 */
	private String source = "一网通办";
	
	/**
	 * 是否转单
	 */
	private boolean isTransfer = false;
	
	/**
	 * 转单原公司
	 */
	private String transferCompany;
	
	/**
	 * 接口返回结果
	 */
	private String ifResult;
	
	/**
	 * 延期次数
	 */
	private int deferTime = 0;
	
	/**
	 * 是否处理
	 */
	private boolean deal = false;
	
	
	private String hourseCertNo;
	
	
	@TableField(exist = false)
	private String shwCompanyName;
	
	@TableField(exist = false)
	private String transferCompanyName;
	
	@TableField(exist = false)
	private String incidentTypeName;
	
	@TableField(exist = false)
	private String statusName;
	
}
