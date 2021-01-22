package com.shanghaiwater.mcs.db.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mcs_shwxzqh_apply")
public class McsShwxzqhApply extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String shwxzqhApplyId;

	private String qxmcApply;

	private String qxdmApply;

	private String jdzmcApply;

	private String jdzdmApply;

	private String companyName;

	private String companyCode;

	private String qxmc;

	private String qxdm;

	private String jdzmc;

	private String jdzdm;

	private String availableFlag;

	private Integer sortNo;

	private String description;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;
}
