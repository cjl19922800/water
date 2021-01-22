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
@TableName("mcs_exception_record")
public class McsExceptionRecord extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String applyNo;
	
	private String isSuccess;
	
	private String code;
	
	private String errcode;
	
	private String message;
	
	private Date createTime;
	
	private String userNo;
	
}
