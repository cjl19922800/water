package com.shanghaiwater.mcs.admin.entity;

import java.time.LocalDateTime;

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
@TableName("mcs_oplog")
public class McsOplog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String oplogId;

	private String eventId;

	private String eventType;

	private String eventObject;

	private String objectType;

	private String operation;

	private String operator;

	private LocalDateTime optime;

	private String eventData;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;

}
