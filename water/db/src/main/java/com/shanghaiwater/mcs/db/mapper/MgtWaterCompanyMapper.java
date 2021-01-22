package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface MgtWaterCompanyMapper extends BaseMapper<MgtWaterCompany> {
	public List<WaterCompany> queryWaterCompany(Map<String, Object> map);
}
