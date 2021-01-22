package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswSingleInvoice;

public interface UswSingleInvoiceService extends IService<UswSingleInvoice> {

	public PageListBasicResponse querySingleInvoiceList(Map<String, Object> map);
}
