package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswMeterMove;
import com.shanghaiwater.mcs.db.vo.MeterMoveIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.PayRemailIncidentMgtVO;

public interface UswMeterMoveMapper extends BaseMapper<UswMeterMove> {

	Integer queryMeterMoveCount(Map paramMap);

	List<MeterMoveIncidentMgtVO> queryMeterMoveList(Map paramMap);

	List<MeterMoveIncidentMgtVO> queryAllMeterMoveList(Map paramMap);
}
