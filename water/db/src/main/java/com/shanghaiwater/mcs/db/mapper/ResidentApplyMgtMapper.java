package com.shanghaiwater.mcs.db.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.vo.ResidentApplyMgtVO;

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
