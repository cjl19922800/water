package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("usw_single_invoice_his")
public class UswSingleInvoiceHis extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ID_WORKER_STR)
	private String singleInvoiceHisId;
	
	private String incidentId;
	
	private Date updateDttm;			//更新时间	
	
	private String businessType;		//业务类型
	
	private String applicant;			//申请人
	
	private String address;  			//用水地址
	
	private String contactValue;		//联系电话
	
	private String email;				//邮箱	
	
	private String title;				//发票抬头
	
	private String billAmount;			//账单金额
	
	private String bank;				//开户行
	
	private String bankCard;			//银行卡号
	
	private String companyName;			//公司名称
	
	private String socialCreditCode;	//社会信用统一编码
	
	private String nsrId;				//纳税人识别号
	
	private String registerAddress;		//注册地址
	
	private String creator;	
	
	private Date cdate;
	
	private String updator;
	
	private Date udate;
	
	private String recordId;
	
	private String recordVersion;
	
	private String account;				//账期
	
	private String meterDate;			//抄表日期
	
	private String copyTime;			//抄次
	
	private String userName;
	
	private String contactInvoice;
}
