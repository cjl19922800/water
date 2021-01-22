package com.shanghaiwater.mcs.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shanghaiwater.mcs.admin.entity.BaseEntity;

import java.util.Date;

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
@TableName("usw_resident_apply_his")
public class UswResidentApplyHis extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String residentApplyHisId;

    private String incidentId;

    private String faId;

    private String cmSta;

    private Date updateDttm;

    private String cisCompany;

    private String businessType;

    private String perName;

    private String xzq;

    private String address;

    private String applyContent;

    private String conName;

    private String phone;

    private String mail;

    private String wygz;

    private String contactAddress;

    private String recordId;

    private String creator;

    private Date cdate;

    private String updator;

    private Date udate;

    private String recordVersion;

}
