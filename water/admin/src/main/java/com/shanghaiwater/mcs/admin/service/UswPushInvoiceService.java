package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswPushInvoice;

public interface UswPushInvoiceService extends IService<UswPushInvoice> {

	public PageListBasicResponse queryPushInvoiceList(Map<String, Object> map);
}
