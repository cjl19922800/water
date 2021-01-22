package com.shanghaiwater.mcs.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.common.vo.WaterCompany;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface IMgtWaterCompanyService extends IService<MgtWaterCompany> {

	List<MgtWaterCompany> findAllCompany();

	List<MgtWaterCompany> findBusinessCompany();

	List<MgtWaterCompany> findByCompanyCode(String companyCode);

	List<WaterCompany> queryByCompanyCode(String companyCode);

	List<WaterCompany> queryWaterBusinessCompany();

}
