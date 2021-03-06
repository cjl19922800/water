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
@TableName("usw_real_name_registe_his")
public class UswRealNameRegisteHis extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String realNameRegisteId;
	
	private String incidentId;			//申请单唯一id
	
	private String faId;				//工单编号
	
	private String cmSta;				//当前状态

	private Date updateDttm;			//更新时间	

	private String businessType;		//业务类型
	
	private String cardId;				//销根号

	private String acctId;				//客户编号
	
	private String applicant;			//申请人
	
	private String address;				//用水地址
	
	private String propertyOwner;		//产权人
	
	private String proOwnerPhone;		//产权人联系电话
	
	private String email;				//邮箱
	
	private String userCardType;		//身份证类型
	
	private String userCardId;			//身份证号
	
	private String houseCertType;		//房产证类型
	
	private String houseCertNo;			//房产证号
	
	private String creator;
	
	private Date cdate;
	
	private String updator;
	
	private Date udate;
	
	private String recordId;
	
	private String recordVersion;
	
	private String applyPhone;			//申请人联系方式

}
