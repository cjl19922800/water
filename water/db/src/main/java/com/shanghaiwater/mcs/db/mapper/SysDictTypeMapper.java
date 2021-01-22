package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.common.vo.DictType;
import com.shanghaiwater.mcs.db.entity.SysDictType;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
	public List<DictType> queryAllDictType(Map<String, Object> map);
}
