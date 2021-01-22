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
@TableName("MCS_DEAL_TRANSFER")
public class McsDealTransfer extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	
	private String applicant;
	
	private String appdate;
	
	private String applyNo;
	
	private String userNo;
	
	private String shwCompany;
	
	private String mobile;
	
}
