package com.shanghaiwater.mcs.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyListResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface UswResidentApplyService extends IService<UswResidentApply> {

	public ResidentApplyListResponse queryList(Map<String, Object> map);

	public ResidentApplyUpdateStatusResponse updateStatus(String incidentId, String status);

	public List<List<String>> queryImages(String incidentId);

	public PageListBasicResponse queryWaterList(Map<String, Object> map);
}
