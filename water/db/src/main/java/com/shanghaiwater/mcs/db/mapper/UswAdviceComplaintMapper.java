package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswAdviceComplaint;
import com.shanghaiwater.mcs.db.vo.AdviceComplainIncidentMgtVO;

public interface UswAdviceComplaintMapper extends BaseMapper<UswAdviceComplaint> {

	
	Integer queryAdviceComplaintCount(Map paramMap);

	List<AdviceComplainIncidentMgtVO> queryAdviceComplaintList(Map paramMap);

	List<AdviceComplainIncidentMgtVO> queryAllAdviceComplaintList(Map paramMap);
}
