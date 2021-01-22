package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.vo.RapirIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.RprUsewaterRepairMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface RprUsewaterRepairMapper extends BaseMapper<RprUsewaterRepair> {
	
	List<RprUsewaterRepairMgtVO> selectRprUsewaterList(Map paramMap);

	Integer selectRprUsewaterCount(Map paramMap);
	
	List<RapirIncidentMgtVO> queryRepairList(Map paramMap);
	
	List<RapirIncidentMgtVO> queryAllRepairList(Map paramMap);
	
	Integer selectRepairCount(Map paramMap);
}
