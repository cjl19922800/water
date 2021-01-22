package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("USW_MULTI_POP_BEN")
public class UswMulitiPopBen extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;//ID
	
	private String incidentId;//申请单ID
	
	private String name;//姓名
	
	private String startDate;//身份证起始日期
	
	private String endDate;//身份证到期日期
	
	private String liveCertType;//居住证明证件类型

	private String certType;//证件类型
	
	private String certNum;//证件号码

	private String address;//地址

	private String checkAddress;//地址核验
	
	private Date cdate;//创建时间
	
}
