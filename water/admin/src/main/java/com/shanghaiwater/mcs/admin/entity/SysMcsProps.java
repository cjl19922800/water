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
@TableName("sys_mcs_props")
public class SysMcsProps extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String propsId;

	private String propName;

	private String propValue;

	private String description;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;

}
