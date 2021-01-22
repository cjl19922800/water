package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.db.entity.SysDictItem;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {
	public List<DictItem> queryAllItemByCode(Map<String, Object> map);
}
