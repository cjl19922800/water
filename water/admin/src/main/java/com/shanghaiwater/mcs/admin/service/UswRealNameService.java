package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswRealNameRegiste;


public interface UswRealNameService extends IService<UswRealNameRegiste> {

	public PageListBasicResponse queryRealNameList(Map<String, Object> map);
}
