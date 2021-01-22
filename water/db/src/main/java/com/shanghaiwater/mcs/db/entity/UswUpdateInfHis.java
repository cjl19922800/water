package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import org.apache.ibatis.javassist.SerialVersionUID;

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
@TableName("usw_update_inf_his")
public class UswUpdateInfHis extends BaseEntity {


	private static final long SerialVersionUID = 1L;


	@TableId(type = IdType.ID_WORKER_STR)
	private String updateInfHisId;
	
	private String incidentId;  	//申请单唯一id

	private String faId;			//工单id	

	private String cmSta;			//当前状态

	private Date updateDttm;		//更新时间

	private String cisCompany;		//供水公司

	private String businessType;		//案例类型
	
	private String cardId;			//销根号
	
	private String acctId;			//客户编号
	
	private String applicant;		//申请人
	
	private String waterAddress;	//用水地址
	
	private String mailAddress;		//邮寄地址
	
	private String postCode;		//邮编
	
	private String contact1;		//联系人1
	
	private String contactNumber1;	//联系电话1
	
	private String contactAddress1;	//联系地址1
	
	private String contact2;		//联系人2
	
	private String contactNumber2;	//联系电话2
	
	private String contactAddress2;	//联系地址2
	
	private Date applyDttm; 		//申请时间
	
	private String remark;			//备注
	
	private String creator;			//创作者
	
	private Date cdate;				//创建时间
	
	private String updator;
	
	private Date udate;
	
	private String recordId;
	
	private String recordVersion;
	
}
