package com.shanghaiwater.mcs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.admin.vo.RprUsewaterRepairMgtVO;

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
	
}
