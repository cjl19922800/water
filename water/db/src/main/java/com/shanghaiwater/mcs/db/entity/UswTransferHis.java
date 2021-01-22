package com.shanghaiwater.mcs.db.entity;

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
@TableName("usw_transfer_his")
public class UswTransferHis extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String transferHisId;

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

	private String comment;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

}
