package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswMulitiPop;
import com.shanghaiwater.mcs.db.vo.MultiPopMgVO;
import com.shanghaiwater.mcs.db.vo.TransferIncidentMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswMulitiPopMapper extends BaseMapper<UswMulitiPop> {

	/**
	 * 查询一户多人口
	 * @param paramMap
	 * @return
	 */
	public List<MultiPopMgVO> queryPopList(Map paramMap);
	
	/**
	 * 查询一户多人口
	 * @param paramMap
	 * @return
	 */
	public List<MultiPopMgVO> queryPopAllList(Map paramMap);

	/**
	 * 一户多人口申请数查询
	 * @param paramMap
	 * @return
	 */
	public Integer queryPopCount(Map paramMap);
	
	
}
