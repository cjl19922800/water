package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswWaterTypeChange;
import com.shanghaiwater.mcs.db.vo.ShareMeterIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.WaterTypeChangeIncidentMgtVO;

public interface UswWaterTypeChangeMapper extends BaseMapper<UswWaterTypeChange> {

	
	Integer queryWaterTypeChangeCount(Map paramMap);

	List<WaterTypeChangeIncidentMgtVO> queryWaterTypeChangeList(Map paramMap);

	List<WaterTypeChangeIncidentMgtVO> queryAllWaterTypeChangeList(Map paramMap);
}
