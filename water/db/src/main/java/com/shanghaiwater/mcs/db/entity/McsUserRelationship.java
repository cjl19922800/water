package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("mcs_user_relationship")
public class McsUserRelationship extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String userId;
	private String dataSource;
	private String ywtbUserId;
	private String companyNo;
	private String companyCode;
	private String userNo;
	private String address;
	private String relationship;
	private Date relaTime;

	private String bankNo;
	private String operId;
	private String fqfs;

	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;
}
