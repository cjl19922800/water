package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.AccHuipanDetail;

public interface AccHuipanDetailMapper extends BaseMapper<AccHuipanDetail> {

	void updateUpstatus(Map<String, String> map);

	List<AccHuipanDetail> findListByCounteroffer(Map<String, String> map);
}
