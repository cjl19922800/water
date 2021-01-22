package com.shanghaiwater.mcs.db.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IncidentBasicVO extends BaseVO{
	
	
	private static final long serialVersionUID = -4759243725564809725L;

	private String incidentId;//申请单ID
	private String userNo;//户号
	private String source;//来源
	private String applyNo;//申请号
	private String status;//状态
	private String statusDict;//状态字典名称
	private String shwCompany;//水务公司code
	private String companyName;//水务公司名称
	private String shwAddress;//用水地址
	private String incidentType;//申请单类型
	private String certType;//证件类型
	private String certTypeDict;//证件类型名称
	private String applicant;//申请人
	private String certNumber;//证件号码
	private String xzq;//行政区
	private String town;//街道
	private String xzqName;//行政区名称
	private String townName;//街道名称
	private Boolean ywtbSuccess;//一网通办接口标识
	private Boolean ywxtSuccess;//业务系统接口标识
	private Date appdate;//申请日期
	private Boolean deal;//处理状态
	private Date expireDate;//到期时间
	private Integer expireStatus;//0:无异常；1-即将到期；2-已到期
	
	private String appdateString;
	private String expireString;
	private String updateTime;
	private String ifResult;
	
}
