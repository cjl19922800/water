package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SYS_RESOURCES")
public class SysResources extends BaseEntity{
	
	private static final long serialVersionUID = 7853559531402045578L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	
	private String name;//名称

	private String url;//资源URL
	
	private String createId;//创建人
	
	private Date createTime;//创建时间
	
	private int sortNo;//排序
	
	private String explain;//说明
	
	private String updateId;//修改人
	
	private Date updateTime;//修改时间
	
	private boolean employ = true;//是否启用
	
	private String navigationId;//外层导航ID
	
	private boolean listResources = false;//是否为外层列表资源
	
	private String modular;//模块
	
	@TableField(exist = false)
	private Boolean checked;
	
}
