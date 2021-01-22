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
@TableName("sys_dict_type")
public class SysDictType extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String dictTypeId;

	private String dictCode;

	private String dictName;

	private String description;

	private String recordId;

	private LocalDateTime cdate;

	private LocalDateTime udate;

	private String recordVersion;

}
