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
@TableName("USW_MULTI_POP_APP_CERT")
public class UswMulitiPopAppCert extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;//ID
	
	private String incidentId;//申请单ID

	private String certType;//证件类型

	private String fileName;//文件名称

	private String fileId;//文件ID
	
	private String scoure;//来源
	
	private Date cdate;//创建时间
	
	@TableField(exist = false)
	private String uri;
	
}
