package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswCompanyApply;
import com.shanghaiwater.mcs.db.vo.CompanyApplyMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswCompanyApplyMapper extends BaseMapper<UswCompanyApply> {
	List<CompanyApplyMgtVO> selectCompanyApplyList(Map paramMap);

	Integer selectCompanyApplyCount(Map paramMap);
}
