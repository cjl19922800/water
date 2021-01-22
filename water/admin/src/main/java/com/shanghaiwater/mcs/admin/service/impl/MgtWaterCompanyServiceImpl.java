package com.shanghaiwater.mcs.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.mapper.MgtWaterCompanyMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Service
public class MgtWaterCompanyServiceImpl extends ServiceImpl<MgtWaterCompanyMapper, MgtWaterCompany>
		implements IMgtWaterCompanyService {

	@Autowired
	private MgtWaterCompanyMapper mgtWaterCompanyMapper;
	
	
	@Override
	public List<MgtWaterCompany> findAllCompany() {

		return this.list();
	}

	@Override
	public List<MgtWaterCompany> findBusinessCompany() {
		QueryWrapper<MgtWaterCompany> queryWrapper = new QueryWrapper<MgtWaterCompany>();
		queryWrapper.eq("type", "B");

		return this.list(queryWrapper);
	}

	@Override
	public List<MgtWaterCompany> findByCompanyCode(String companyCode) {

		QueryWrapper<MgtWaterCompany> queryWrapper = new QueryWrapper<MgtWaterCompany>();
		queryWrapper.eq("company_code", companyCode);

		return this.list(queryWrapper);
	}
	
	/****以下为从mcs-service-masterdata包移植方法*****/
	@Override
	public List<WaterCompany> queryByCompanyCode(String companyCode) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("companyCode", companyCode);

		return mgtWaterCompanyMapper.queryWaterCompany(map);
	}
	
	@Override
	public List<WaterCompany> queryWaterBusinessCompany() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("type", "B");

		return mgtWaterCompanyMapper.queryWaterCompany(map);
	}
	

}
