package com.shanghaiwater.mcs.admin.service;

import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.admin.model.TransferRequest;
import com.shanghaiwater.mcs.admin.model.TransferResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferListResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferUpdateStatusResponse;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswCompanyTransferService extends IService<UswTransfer> {

	// New
		public UswTransferListResponse queryList(Map<String, Object> map);

		public UswTransferUpdateStatusResponse updateStatus(String incidentId, String status);

		public List<List<String>> queryImages(String incidentId);

}
