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
@TableName("sys_multi_code_detail")
public class SysMultiCodeDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String multiCodeDetailId;

	private String codeType;

	private String parentItemCode;

	private String itemCode;

	private String itemOtherCode;

	private String itemValue;

	private Integer level_;

	private String status;

	private Integer sortNo;

	private String description;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;
	
	private String fenshuo;
	
	private String companyCode;
}
