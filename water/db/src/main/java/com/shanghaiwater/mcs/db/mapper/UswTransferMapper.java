package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.db.vo.ResidentTransferMgtVO;
import com.shanghaiwater.mcs.db.vo.TransferIncidentMgtVO;

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
	
	List<TransferIncidentMgtVO> queryTransferList(Map paramMap);
	
	List<TransferIncidentMgtVO> queryAllTransferList(Map paramMap);

	Integer queryTransferCount(Map paramMap);

}
