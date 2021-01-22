package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mcs_huipan")
public class AccHuipan {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String counterofferId;
	private String appid;
	private String companyCode; // 单位编码
	private String companyName; // 单位名称
	private String trandate; // 日期
	private String amount; // 总金额
	private String totalRecordCount; // 总数据行数目
	private String normalAmount; // 正常清算金额
	private String normalRecordCount; // 正常清算数据行数目
	private String refundAmount; // 退款总金额
	private String refundRecordCount;// 退款数据行数目

	private String recordId;
	private String creator;
	private Date cdate;
	private String updator;
	private Date udate;
	private String recordVersion;

}
