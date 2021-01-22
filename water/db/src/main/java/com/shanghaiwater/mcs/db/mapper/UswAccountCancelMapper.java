package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswAccountCancel;
import com.shanghaiwater.mcs.db.vo.AccountCancelIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.MeterMoveIncidentMgtVO;

public interface UswAccountCancelMapper extends BaseMapper<UswAccountCancel> {

	Integer queryAccountCancelCount(Map paramMap);

	List<AccountCancelIncidentMgtVO> queryAccountCancelList(Map paramMap);

	List<AccountCancelIncidentMgtVO> queryAllAccountCancelList(Map paramMap);
	
}
