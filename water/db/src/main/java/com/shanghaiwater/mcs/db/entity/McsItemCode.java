package com.shanghaiwater.mcs.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MCS_ITEM_CODE")
public class McsItemCode extends BaseEntity{
	
	private static final long serialVersionUID = -2914060600169475334L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	
	private String incidentType;
	
	private String itemCode;
	
	private String zzkItemCode;
	
	private String itemName;
	
	private String businessType;
	
	private String type;
	
	private String customizeCode;
	
	private Integer proccessLevel; 
	
	private Integer proccessTime; 
	
	private String eventId;
	
	private boolean logic = false;
	
}
