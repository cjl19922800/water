package com.shanghaiwater.mcs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.admin.vo.UswWatermeterSplitMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswWatermeterSplitMapper extends BaseMapper<UswWatermeterSplit> {
	List<UswWatermeterSplitMgtVO> selectUswWatermeterSplitList(Map paramMap);

	Integer selectUswWatermeterSplitCount(Map paramMap);

}
