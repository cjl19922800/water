package com.shanghaiwater.mcs.db.mapper;

import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.AccDiffFileDataDetail;

public interface AccDiffFileDataDetailMapper extends BaseMapper<AccDiffFileDataDetail> {
	@Update("update MCS_DIFF_FILEDATA_DETAIL set mcs_status = 'OK' where mcs_status = 'INIT'")
	int updateMcsStatusB();
}
