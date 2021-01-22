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
@TableName("sys_dict_item")
public class SysDictItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String dictItemId;

	private String dictCode;

	private String itemCode;

	private String itemValue;

	private String status;

	private Integer sortNo;

	private String description;

	private String recordId;

	private LocalDateTime cdate;

	private LocalDateTime udate;

	private String recordVersion;

}
