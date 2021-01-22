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
@TableName("mgt_item_code")
public class MgtItemCode extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String itemCodeId;

	private String itemCode;

	private String name;

	private String type;

	private String status;

	private Integer sortNo;

	private String description;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;

}
