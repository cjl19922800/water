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
@TableName("usw_transfer")
public class UswTransfer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;

	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String businessType;

	private String cardId;

	private String acctId;

	private Date fyDttm;

	private String ghMeterRead;

	private String amt;

	private String newEntityName;

	private String idType;

	private String idNbr;

	private String fczjlx;

	private String fczjhm;

	private String address;

	private String commRteTypeCd;

	private String contactValue;

	private String gyshtbh;

	private String fplx;

	private String fptt;

	private String nsrsbh;

	private String qylx;

	private String ysrks;
	
	private String transferComment;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

	private String billAddress;//账单邮寄地址
	
	private String transferReason;//过户原因
	
	private String useType;//客户类型（房屋使用类型）
	
	private String contacts;//联系人
	
	private String contactNum;//联系电话
	
	private String bdczl;//不动产坐落
	
	private String bdcdbrq;//不动产登簿日期
	
	private Integer source;//联合过户来源
	
	
	private long bdc_id;//不动产登记业务号
	private String rightno;//不动产权证号
	private String location;//不动产坐落
	private String bdcsh;//室号
	
	
	
	
	
}
