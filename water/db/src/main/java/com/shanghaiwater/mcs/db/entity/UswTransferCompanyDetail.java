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
@TableName("USW_TRANSFER_COMPANY_DETAIL")
public class UswTransferCompanyDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	
	private String incidentId;//申请单唯一标识
	
	private String userNo;//户号
	
	private boolean ywxtSuccess = false;//业务系统返回结果
	
	private Date createTime;//创建时间
	
	private String createId;//创建人
	
	private String ywxtMsg;//业务系统返回信息
	
	private String msg;//最终用户展示信息
	
	private boolean sales = false;//是否销单（暂留）
	
	private String recordId;//记录ID（传给业务系统的ID）

	private String address;//地址
}
