package com.shanghaiwater.mcs.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferListResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferUpdateStatusResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswTransferService extends IService<UswTransfer> {

	// New
	public UswTransferListResponse queryList(Map<String, Object> map);

	public UswTransferUpdateStatusResponse updateStatus(String incidentId, String status);

	public List<List<String>> queryImages(String incidentId);

	public PageListBasicResponse queryTransferList(Map<String, Object> map);

}
