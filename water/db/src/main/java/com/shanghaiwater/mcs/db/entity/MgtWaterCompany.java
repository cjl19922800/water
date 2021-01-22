package com.shanghaiwater.mcs.db.entity;

import java.time.LocalDateTime;

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
@TableName("mgt_water_company")
public class MgtWaterCompany extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String waterCompanyId;

	private String companyCode;

	private String name;

	private String shortName;

	private String sortNo;
	
	private String address;

	private String description;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;

}
