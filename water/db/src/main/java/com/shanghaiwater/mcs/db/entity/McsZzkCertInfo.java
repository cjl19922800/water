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
@TableName("MCS_ZZK_CERT_INFO")
public class McsZzkCertInfo extends BaseEntity {
	
	private static final long serialVersionUID = -2390867412785238879L;
	
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	
	private String incidentId;
	
	private String fileName;
	
	private String jsonData;
	
	private String type;
	
	private Date createTime;
	
	private String companyCode;
	
}
