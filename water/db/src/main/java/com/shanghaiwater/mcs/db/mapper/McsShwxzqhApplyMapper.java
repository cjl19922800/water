package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.common.vo.AreaTown;
import com.shanghaiwater.mcs.db.entity.McsShwxzqhApply;

public interface McsShwxzqhApplyMapper extends BaseMapper<McsShwxzqhApply> {
	public List<AreaTown> queryShwxzqhApply(Map<String, Object> map);
	
	public List<AreaTown> selectAllArea();
}
