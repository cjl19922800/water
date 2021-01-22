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
@TableName("SYS_NAVIGATION")
public class SysNavigation extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	
	private String name;//名称
	
	private String createrId;//创建人
	
	private Date createTime;//创建时间
	
	private int sortNo;//排序
	
	private String explain;//说明
	
	private String updateId;//修改人
	
	private Date updateTime;//修改时间
	
	private boolean employ = true;
	
	private String parentId;//父级ID

}
