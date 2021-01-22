package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.db.vo.SplitIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.UswWatermeterSplitMgtVO;

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
	
	List<SplitIncidentMgtVO> querySplitList(Map paramMap);
	
	Integer querySplitCount(Map paramMap);
	
	List<SplitIncidentMgtVO> queryAllSplitList(Map paramMap);
}
