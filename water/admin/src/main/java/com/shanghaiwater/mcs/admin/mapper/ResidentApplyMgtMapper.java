package com.shanghaiwater.mcs.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.vo.ResidentApplyMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface ResidentApplyMgtMapper extends BaseMapper<ResidentApplyMgtVO> {
	List<ResidentApplyMgtVO> selectResidentApplyList();
}
