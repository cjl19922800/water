package com.shanghaiwater.mcs.db.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.AccCollectFee;

public interface AccCollectFeeMapper extends BaseMapper<AccCollectFee> {
	
	public List<AccCollectFee> findAccCollectFee(String flow_no);

}
