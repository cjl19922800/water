package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswOpenMeter;
import com.shanghaiwater.mcs.db.vo.OpenMeterIncidentMgtVO;

public interface UswOpenMeterMapper extends BaseMapper<UswOpenMeter> {

	
	Integer queryOpenMeterCount(Map paramMap);

	List<OpenMeterIncidentMgtVO> queryOpenMeterList(Map paramMap);

	List<OpenMeterIncidentMgtVO> queryAllOpenMeterList(Map paramMap);
}
