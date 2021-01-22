package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswChargeCollection;
import com.shanghaiwater.mcs.db.vo.ChargeCollectionIncidentMgtVO;

public interface UswChargeCollectionMapper extends BaseMapper<UswChargeCollection> {

	
	Integer queryChargeCollectionCount(Map paramMap);

	List<ChargeCollectionIncidentMgtVO> queryChargeCollectionList(Map paramMap);

	List<ChargeCollectionIncidentMgtVO> queryAllChargeCollectionList(Map paramMap);
}
