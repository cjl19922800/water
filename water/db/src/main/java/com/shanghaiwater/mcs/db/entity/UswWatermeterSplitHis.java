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
@TableName("usw_watermeter_split_his")
public class UswWatermeterSplitHis extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String watermeterSplitHisId;

	private String incidentId;

	private String faId;

	private String cmSta;

	private Date updateDttm;

	private String cisCompany;

	private String businessType;

	private String perName;

	private String address;

	private String conName;

	private String contactValue;

	private String phone;

	private String mail;

	private String yb;

	@TableField("split_his_comment")
	private String splitComment;

	private String wygz;

	private String recordId;

	private String creator;

	private Date cdate;

	private String updator;

	private Date udate;

	private String recordVersion;

}
