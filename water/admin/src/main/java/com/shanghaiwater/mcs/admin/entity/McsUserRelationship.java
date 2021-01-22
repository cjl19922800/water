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
@TableName("mcs_user_relationship")
public class McsUserRelationship extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userRelationshipId;

	private String userUuid;

	private String userId;

	private String username;

	private String branchCode;

	private String cardId;

	private String acctId;

	private String address;

	private String relationship;

	private LocalDateTime relaTime;

	private String recordId;

	private String creator;

	private LocalDateTime cdate;

	private String updator;

	private LocalDateTime udate;

	private String recordVersion;

}
