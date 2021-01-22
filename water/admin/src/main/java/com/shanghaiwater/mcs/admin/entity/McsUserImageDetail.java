package com.shanghaiwater.mcs.admin.entity;

import java.util.Date;

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
@TableName("mcs_user_image_detail")
public class McsUserImageDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("user_image_detaile_id")
    private String userImageDetaileId;

    private String userImageId;

    private String certType;

    private String certNumber;

    private String imageUuid;

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
    
    private String applyNo;


}
