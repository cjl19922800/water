package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.admin.model.IncidentListResponse;
import com.shanghaiwater.mcs.admin.service.McsIncidentService;
import com.shanghaiwater.mcs.db.vo.IncidentMgtVO;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Service
public class McsIncidentServiceImpl extends ServiceImpl<McsIncidentMapper, McsIncident> implements McsIncidentService {

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;

	private final static String[] imgType = { "居民住宅接水申请表", "身份证", "产权证" };

	@Override
	public IncidentListResponse queryList(Map map) {
		IncidentListResponse incidentListResponse = new IncidentListResponse();

		Integer total = mcsIncidentMapper.selectIncidentCount(map);

		List<IncidentMgtVO> data = mcsIncidentMapper.selectIncidentList(map);
		incidentListResponse.setData(data);

		incidentListResponse.setCode(0);
		incidentListResponse.setSuccess(true);
		incidentListResponse.setCount(total + "");
		incidentListResponse.setTotal(total);

		return incidentListResponse;
	}

}
