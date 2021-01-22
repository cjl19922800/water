package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.McsIncidentTemp;
import com.shanghaiwater.mcs.db.vo.ApiIncidentVo;

public interface McsIncidentTempMapper extends BaseMapper<McsIncidentTemp> {
	
	List<ApiIncidentVo> selectApiIncidentList(Map paramMap);
	
}
