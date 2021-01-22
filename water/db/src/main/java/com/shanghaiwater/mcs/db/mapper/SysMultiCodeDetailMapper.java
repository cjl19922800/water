package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.common.vo.MultiCodeDetail;
import com.shanghaiwater.mcs.db.entity.SysMultiCodeDetail;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysMultiCodeDetailMapper extends BaseMapper<SysMultiCodeDetail> {

	public List<MultiCodeDetail> queryMultiCodeDetail(Map<String, Object> map);
}
