package com.shanghaiwater.mcs.db.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("MCS_DIFF_FILEDATA")
public class AccDiffFileData implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String diffFiledataId;

	private String companyCode;
	private String dayString;
	private String money;
	private String count;
	private String mcsStatus;

	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;
}
