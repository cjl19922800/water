package com.shanghaiwater.mcs.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.service.SysMultiCodeDetailService;
import com.shanghaiwater.mcs.common.enums.CompanyEnum;
import com.shanghaiwater.mcs.common.vo.MultiCodeDetail;
import com.shanghaiwater.mcs.db.entity.SysMultiCodeDetail;
import com.shanghaiwater.mcs.db.mapper.SysMultiCodeDetailMapper;

@Service
public class SysMultiCodeDetailServiceImpl extends ServiceImpl<SysMultiCodeDetailMapper, SysMultiCodeDetail>
		implements SysMultiCodeDetailService {

	@Autowired
	private SysMultiCodeDetailMapper sysMultiCodeDetailMapper;
	
	
	public List<SysMultiCodeDetail> findListByMultiCode(String codeType, String parentItemCode){
		QueryWrapper<SysMultiCodeDetail> queryWrapper = new QueryWrapper<SysMultiCodeDetail>();
		queryWrapper.eq("code_type", codeType);
		queryWrapper.eq("parent_item_code", parentItemCode);
		queryWrapper.eq("STATUS", "Enable");
		queryWrapper.orderByAsc("SORT_NO");
		return this.list(queryWrapper);
	}

	public SysMultiCodeDetail findListByItemCode(String codeType, String parentItemCode, String itemCode)
	{
		QueryWrapper<SysMultiCodeDetail> queryWrapper = new QueryWrapper<SysMultiCodeDetail>();
		queryWrapper.eq("code_type", codeType);
		queryWrapper.eq("parent_item_code", parentItemCode);
		queryWrapper.eq("item_code", itemCode);
		queryWrapper.eq("STATUS", "Enable");
		queryWrapper.orderByAsc("SORT_NO");
		return this.getOne(queryWrapper);
	}

	@Override
	public List<SysMultiCodeDetail> queryJdByXzq(String itemOtherCode){
		QueryWrapper<SysMultiCodeDetail> qw = new QueryWrapper<SysMultiCodeDetail>();
		qw.eq("CODE_TYPE", "SHWXZQH");
		qw.eq("STATUS", "Enable");
		qw.eq("ITEM_OTHER_CODE", itemOtherCode);
		List<String> companyCodes =CompanyEnum.getAllCompanyCodes();
		qw.notIn("PARENT_ITEM_CODE", companyCodes);
		List<SysMultiCodeDetail> list = sysMultiCodeDetailMapper.selectList(qw);
		return list;
	}
	
	@Override
	public SysMultiCodeDetail getDetailByItemCode(String itemCode) {
		QueryWrapper<SysMultiCodeDetail> qw = new QueryWrapper<SysMultiCodeDetail>();
		qw.eq("CODE_TYPE", "SHWXZQH");
		qw.eq("STATUS", "Enable");
		qw.eq("ITEM_CODE", itemCode);
		SysMultiCodeDetail detail = sysMultiCodeDetailMapper.selectOne(qw);
		return detail;
	}
	
	
	
	@Override
	public List<SysMultiCodeDetail> queryAllXzq(){
		QueryWrapper<SysMultiCodeDetail> qw = new QueryWrapper<SysMultiCodeDetail>();
		qw.eq("CODE_TYPE", "SHWXZQH");
		qw.eq("STATUS", "Enable");
		List<String> companyCodes =CompanyEnum.getAllCompanyCodes();
		qw.in("PARENT_ITEM_CODE", companyCodes);
		qw.inSql("MULTI_CODE_DETAIL_ID", 
				"select b.multi_code_detail_id from (select a.multi_code_detail_id, row_number() OVER(PARTITION BY a.item_other_code order by sort_no) as row_flg from sys_multi_code_detail a ) b where b.row_flg = '1'");
		List<SysMultiCodeDetail> list = sysMultiCodeDetailMapper.selectList(qw);
		return list;
	}
	
	
	
	@Override
	public List<MultiCodeDetail> queryByCodeType(String codeType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeType", codeType);
		return sysMultiCodeDetailMapper.queryMultiCodeDetail(map);
	}

	@Override
	public List<MultiCodeDetail> queryByParentItemCode(String codeType, String parentItemCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeType", codeType);
		map.put("parentItemCode", parentItemCode);
		return sysMultiCodeDetailMapper.queryMultiCodeDetail(map);
	}

	@Override
	public List<MultiCodeDetail> queryByItemCode(String codeType, String parentItemCode, String itemCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeType", codeType);
//		map.put("parentItemCode", parentItemCode);
		map.put("itemCode", itemCode);
		return sysMultiCodeDetailMapper.queryMultiCodeDetail(map);
	}
	
	
}
