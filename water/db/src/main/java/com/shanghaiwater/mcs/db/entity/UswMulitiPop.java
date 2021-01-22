package com.shanghaiwater.mcs.db.entity;

import java.util.Date;

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
@TableName("USW_MULTI_POP")
public class UswMulitiPop extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String incidentId;//申请单ID

	private String applyAddress;//申请人房屋地址

	private String addressCheck;//地址校验结果

	private String reqType;//办件类型
	
	private String peopleNum;//受益人数
	
	private String settleMethod;//选择的优惠方式
	
	private String operType;//办件类型
	
	private String applicant;//申请人
	
	private String company;//水司
	
	private String phone;//申请人电话
	
	private String creator;//创建人
	
	private String certType;//证件类型
	
	private String certNum;//证件号码
	
	private Date cdate;//创建时间
	
}
