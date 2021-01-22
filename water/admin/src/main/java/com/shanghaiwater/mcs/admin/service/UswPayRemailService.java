package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswPayRemailApply;
import com.shanghaiwater.mcs.db.entity.UswRealNameRegiste;


public interface UswPayRemailService extends IService<UswPayRemailApply> {

	public PageListBasicResponse queryPayRemailList(Map<String, Object> map);
}
