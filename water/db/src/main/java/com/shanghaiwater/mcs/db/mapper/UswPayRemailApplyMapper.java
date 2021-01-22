package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswPayRemailApply;
import com.shanghaiwater.mcs.db.vo.PayRemailIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.RealNameIncidentMgtVO;

public interface UswPayRemailApplyMapper extends BaseMapper<UswPayRemailApply> {

	Integer queryPayRemailCount(Map paramMap);
	
	List<PayRemailIncidentMgtVO> queryPayRemailList(Map paramMap);
	
	List<PayRemailIncidentMgtVO> queryAllPayRemailList(Map paramMap);
	
}
