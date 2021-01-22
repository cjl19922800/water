package com.shanghaiwater.mcs.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.service.SysDictItemService;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.db.entity.SysDictItem;
import com.shanghaiwater.mcs.db.mapper.SysDictItemMapper;

@Service
public class DictCommonServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {
	
	@Autowired
	private SysDictItemMapper sysDictItemMapper;
	
	
	@Override
	public List<SysDictItem> findCodeList(String dictCode) {
		QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<SysDictItem>();
		queryWrapper.eq("DICT_CODE", dictCode);
		queryWrapper.eq("STATUS", "Enable");
		queryWrapper.orderByAsc("SORT_NO");

		return this.list(queryWrapper);
	}
	@Override
	public List<SysDictItem> findLogicCodeList(String dictCode,String logic) {
		QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<SysDictItem>();
		queryWrapper.eq("DICT_CODE", dictCode);
		queryWrapper.eq("STATUS", "Enable");
		queryWrapper.eq("LOGIC", logic);
		queryWrapper.orderByAsc("SORT_NO");
		return this.list(queryWrapper);
	}

	@Override
	public String findItemValue(String dictCode, String itemCode) {
		QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<SysDictItem>();
		queryWrapper.eq("DICT_CODE", dictCode);
		queryWrapper.eq("item_code", itemCode);
		queryWrapper.eq("STATUS", "Enable");
		queryWrapper.orderByAsc("SORT_NO");

		List<SysDictItem> itemList = this.list(queryWrapper);
		if (itemList == null || itemList.size() == 0) {
			return null;
		} else {
			return itemList.get(0).getItemValue();
		}
	}
	
	
	/****以下为从mcs-service-masterdata包移植方法*****/
	@Override
	public List<DictItem> queryAllItemByCode(String dictCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictCode", dictCode);
		return sysDictItemMapper.queryAllItemByCode(map);
	}

	@Override
	public List<DictItem> queryItemByCode(String dictCode, String itemCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictCode", dictCode);
		map.put("itemCode", itemCode);
		List<DictItem> dictItemList = sysDictItemMapper.queryAllItemByCode(map);
		return dictItemList;
	}
	

}
