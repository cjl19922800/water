package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @author Will
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("usw_pay_remail_apply")
public class UswPayRemailApply extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;			//申请单唯一id
	
	private String faId;				//工单编号
	
	private String cmSta;				//当前状态

	private Date updateDttm;			//更新时间	

	private String businessType;		//业务类型
	
	private String cardId;				//销根号

	private String acctId;				//客户编号
	
	private String perName;				//申请人姓名
	
	private String address;				//用水地址
	
	private String contactValue;		//联系电话
	
	private String addressee;			//收件人
	
	private String addPhone;			//收件人电话
	
	private String mailAddress;			//邮寄地址
	
	private String postCode;			//邮编
	
	private String billDate;			//账单日期
	
	private String creator;
	
	private Date cdate;
	
	private String updator;
	
	private Date udate;
	
	private String recordId;
	
	private String recordVersion;
	
	private String noticeType;
}
