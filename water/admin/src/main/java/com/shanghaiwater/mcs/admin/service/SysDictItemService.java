package com.shanghaiwater.mcs.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.db.entity.SysDictItem;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysDictItemService extends IService<SysDictItem> {
	public List<SysDictItem> findCodeList(String dictCode);

	public String findItemValue(String dictCode, String itemCode);

	public List<DictItem> queryItemByCode(String dictCode, String itemCode);

	public List<DictItem> queryAllItemByCode(String dictCode);

	public List<SysDictItem> findLogicCodeList(String dictCode, String logic);
}
