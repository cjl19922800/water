package com.shanghaiwater.mcs.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.common.vo.MultiCodeDetail;
import com.shanghaiwater.mcs.db.entity.SysMultiCodeDetail;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysMultiCodeDetailService extends IService<SysMultiCodeDetail> {
	public List<SysMultiCodeDetail> findListByMultiCode(String codeType, String parentItemCode);

	public SysMultiCodeDetail findListByItemCode(String codeType, String parentItemCode, String itemCode);

	public List<MultiCodeDetail> queryByCodeType(String codeType);

	public List<MultiCodeDetail> queryByParentItemCode(String codeType, String parentItemCode);

	public List<MultiCodeDetail> queryByItemCode(String codeType, String parentItemCode, String itemCode);

	public List<SysMultiCodeDetail> queryAllXzq();

	public List<SysMultiCodeDetail> queryJdByXzq(String itemOtherCode);

	public SysMultiCodeDetail getDetailByItemCode(String itemCode);

}
