package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswUpdateInf;
import com.shanghaiwater.mcs.db.vo.CusChangeIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.PayRemailIncidentMgtVO;

public interface UswUpdateInfMapper extends BaseMapper<UswUpdateInf>{

	
	Integer queryCusChangeCount(Map paramMap);
	
	List<CusChangeIncidentMgtVO> queryCusChangeList(Map paramMap);
	
	List<CusChangeIncidentMgtVO> queryAllCusChangeList(Map paramMap);
}
