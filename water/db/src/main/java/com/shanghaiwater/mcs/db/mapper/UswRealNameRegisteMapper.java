package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.shanghaiwater.mcs.db.entity.UswRealNameRegiste;
import com.shanghaiwater.mcs.db.vo.RealNameIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.RealNameMgtVO;

public interface UswRealNameRegisteMapper extends BaseMapper<UswRealNameRegiste> {

	
	
	Integer queryRealNameCount(Map paramMap);
	
	List<RealNameIncidentMgtVO> queryRealNameList(Map paramMap);
	
	List<RealNameIncidentMgtVO> queryAllRealNameList(Map paramMap);
}
