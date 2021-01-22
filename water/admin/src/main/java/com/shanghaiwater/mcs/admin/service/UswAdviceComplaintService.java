package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswAdviceComplaint;

public interface UswAdviceComplaintService extends IService<UswAdviceComplaint> {

	public PageListBasicResponse queryAdviceComplaintList(Map<String, Object> map);
}
