package com.shanghaiwater.mcs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.entity.UswTransfer;
import com.shanghaiwater.mcs.admin.vo.ResidentTransferMgtVO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswTransferMapper extends BaseMapper<UswTransfer> {
	List<ResidentTransferMgtVO> selectResidentTransferList(Map paramMap);

	Integer selectResidentTransferCount(Map paramMap);

}
