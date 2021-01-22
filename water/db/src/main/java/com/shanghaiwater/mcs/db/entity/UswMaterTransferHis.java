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
@TableName("USW_MATER_TRANSFER_HIS")
public class UswMaterTransferHis extends BaseEntity{
	
	private static final long serialVersionUID = 7908507597779554252L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String incidentId;
	
	private String bookTime;
	
	private String contactNum;
	
	private String certType;
	
	private String certNumber;
	
	private String creator;
	
	private Date cdate;
	
}
