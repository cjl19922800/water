package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;

public interface SysAdminLogService {

	public void saveAdminLog(OperationEnums oe, ModuleEnums me, String msg, String tagetId);

	public CommonPageReponse queryPagingLogList(Map<String, String> map);

}
