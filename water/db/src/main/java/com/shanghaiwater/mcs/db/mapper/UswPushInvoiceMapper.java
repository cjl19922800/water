package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswPushInvoice;
import com.shanghaiwater.mcs.db.vo.PushInvoiceIncidentMgtVO;

public interface UswPushInvoiceMapper extends BaseMapper<UswPushInvoice> {

	Integer queryPushInvocieCount(Map paramMap);
	
	List<PushInvoiceIncidentMgtVO> queryPushInvoiceList(Map paramMap);
	
	List<PushInvoiceIncidentMgtVO> queryAllPushInvoiceList(Map paramMap);
	
}
