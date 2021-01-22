package com.shanghaiwater.mcs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.entity.McsIncident;
import com.shanghaiwater.mcs.admin.vo.IncidentMgtVO;

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
}
