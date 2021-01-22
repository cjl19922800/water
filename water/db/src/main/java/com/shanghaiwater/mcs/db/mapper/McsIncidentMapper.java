package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.vo.ApiIncidentVo;
import com.shanghaiwater.mcs.db.vo.IncidentMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface McsIncidentMapper extends BaseMapper<McsIncident> {
	List<IncidentMgtVO> selectIncidentList(Map paramMap);

	Integer selectIncidentCount(Map paramMap);
	
	List<ApiIncidentVo> selectApiIncidentList(Map paramMap);
	
	
	List<ApiIncidentVo> selectApiIncidentListAll(Map paramMap);
	
	
	
	
	
}
