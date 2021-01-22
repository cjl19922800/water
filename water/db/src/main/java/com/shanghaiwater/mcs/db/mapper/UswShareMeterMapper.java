package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswShareMeter;
import com.shanghaiwater.mcs.db.vo.ShareMeterIncidentMgtVO;

public interface UswShareMeterMapper extends BaseMapper<UswShareMeter> {

	
	Integer queryShareMeterCount(Map paramMap);

	List<ShareMeterIncidentMgtVO> queryShareMeterList(Map paramMap);

	List<ShareMeterIncidentMgtVO> queryAllShareMeterList(Map paramMap);
}
