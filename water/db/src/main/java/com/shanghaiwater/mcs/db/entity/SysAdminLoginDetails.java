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
@TableName("SYS_ADMIN_LOGIN_DETAILS")
public class SysAdminLoginDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;//主键ID

	private String userId;//用户ID

	private int loginTimes;//登录次数

	private boolean locking = false;//是否失败次数锁定

	private Integer loginErrorTimes;//连续登录失败次数
	
	private Date lockingTime;//锁定（到）时间
	
	private Date expireTime;//到期时间
	
	private Date latelyTime;//最近一次登录时间
	
}
