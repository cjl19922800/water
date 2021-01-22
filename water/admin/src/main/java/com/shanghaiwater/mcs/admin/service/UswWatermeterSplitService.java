package com.shanghaiwater.mcs.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.UswWatermeterSplitListResponse;
import com.shanghaiwater.mcs.admin.model.UswWatermeterSplitUpdateStatusResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswWatermeterSplitService extends IService<UswWatermeterSplit> {
	// New
	public UswWatermeterSplitListResponse queryList(Map<String, Object> map);

	public UswWatermeterSplitUpdateStatusResponse updateStatus(String incidentId, String status);

	public List<List<String>> queryImages(String incidentId);

	public PageListBasicResponse querySplitList(Map<String, Object> map);
}
