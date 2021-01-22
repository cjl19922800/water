package com.shanghaiwater.mcs.db.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.UswSingleInvoice;
import com.shanghaiwater.mcs.db.vo.PushInvoiceIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.SingleInvoiceIncidentMgtVO;

public interface UswSingleInvoiceMapper extends BaseMapper<UswSingleInvoice> {

	Integer querySingleInvoiceCount(Map paramMap);

	List<SingleInvoiceIncidentMgtVO> querySingleInvocieList(Map paramMap);

	List<SingleInvoiceIncidentMgtVO> queryAllSingleInvoiceList(Map paramMap);
}
