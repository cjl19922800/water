package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.vo.ResidentApplyMgtVO;
import com.shanghaiwater.mcs.db.vo.ResidentWaterIncidentMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswResidentApplyMapper extends BaseMapper<UswResidentApply> {
	List<ResidentApplyMgtVO> selectResidentApplyList(Map paramMap);

	Integer selectResidentApplyCount(Map paramMap);
	
	List<ResidentWaterIncidentMgtVO> queryResidentList(Map paramMap);
	
	List<ResidentWaterIncidentMgtVO> queryAllResidentList(Map paramMap);

	Integer selectWaterCount(Map paramMap);
}
