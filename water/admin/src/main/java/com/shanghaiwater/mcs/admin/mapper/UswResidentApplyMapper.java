package com.shanghaiwater.mcs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.entity.UswResidentApply;
import com.shanghaiwater.mcs.admin.vo.ResidentApplyMgtVO;

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
}
