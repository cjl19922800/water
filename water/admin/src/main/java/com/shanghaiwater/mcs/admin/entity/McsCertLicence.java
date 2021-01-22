package com.shanghaiwater.mcs.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shanghaiwater.mcs.admin.entity.BaseEntity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("mcs_cert_licence")
public class McsCertLicence extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String certLicenceId;

    private String certType;

    private String certNumber;

    @TableField("businessCode")
    private String businessCode;

    @TableField("itemCode")
    private String itemCode;

    @TableField("itemName")
    private String itemName;

    @TableField("orgName")
    private String orgName;

    private String username;

    private Integer fileQuantity;

    private String description;

    private String recordId;

    private String creator;

    private Date cdate;

    private String updator;

    private Date udate;

    private String recordVersion;


}
