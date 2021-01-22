package com.shanghaiwater.mcs.db.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mcs_diff_duizhang")
public class AccDiffDuizhang implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String diffWxtId;

	private String companyCode;
	private String dayString;
	private String money;
	private String count;
	private String status;

	private String step1;
	private String step2;
	private String step3;
	private String step4;
	private String step5;
	private String step6;
	private String step7;

	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;
}
