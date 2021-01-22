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
@TableName("mcs_cert_licence_detail")
public class McsCertLicenceDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String certLicenceDetailId;

    private String certLicenceId;

    private String certType;

    private String certNumber;

    private String certUuid;

    private String fileNo;

    private String fileType;

    private String filename;

    private String localUri;

    private String webUri;

    private String ftpUri;

    private Integer sortNo;

    private String recordId;

    private String creator;

    private Date cdate;

    private String updator;

    private Date udate;

    private String recordVersion;


}
