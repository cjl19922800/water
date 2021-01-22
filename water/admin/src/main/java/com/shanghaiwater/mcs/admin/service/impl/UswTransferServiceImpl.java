package com.shanghaiwater.mcs.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.McsUserImageDetail;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswTransferMapper;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferListResponse;
import com.shanghaiwater.mcs.admin.model.UswTransferUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.UswTransferService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.db.vo.ResidentTransferMgtVO;
import com.shanghaiwater.mcs.db.vo.TransferIncidentMgtVO;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.common.vo.WaterCompany;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Service
public class UswTransferServiceImpl extends ServiceImpl<UswTransferMapper, UswTransfer> implements UswTransferService {

	@Autowired
	private UswTransferMapper uswTransferMapper;

	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;

	@Autowired
	private CisCompanyCodeService cisCompanyCodeService;

	private final static String[] imgType = { "身份证", "房屋产权证", "缴费通知单", "居民过户申请表" };
	
	
	@Override
	public PageListBasicResponse queryTransferList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswTransferMapper.queryTransferCount(map);
		List<TransferIncidentMgtVO> list = uswTransferMapper.queryTransferList(map);
		
		for(TransferIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setFczjhm(SecurityUtil.decryptAES(vo.getFczjhm()));
			vo.setContactValue(SecurityUtil.decryptAES(vo.getContactValue()));
		}
		
		response.setData(list);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		return response;
	}
	

	@Override
	public UswTransferListResponse queryList(Map<String, Object> map) {
		UswTransferListResponse result = new UswTransferListResponse();
		Integer count = uswTransferMapper.selectResidentTransferCount(map);
		List<ResidentTransferMgtVO> list2 = uswTransferMapper.selectResidentTransferList(map);
		list2.forEach(l -> {
			List<WaterCompany> itemCode = null; // TODO
			String code = itemCode.get(0).getName();
			l.setCisCompany(code);
		});
		result.setData(list2);
		result.setCode(0);
		result.setCount(count + "");
		return result;
	}

	@Override
	public UswTransferUpdateStatusResponse updateStatus(String incidentId, String status) {
		UswTransferUpdateStatusResponse residentTransferUpdateStatusResponse = new UswTransferUpdateStatusResponse();

		McsIncident mcsIncident = mcsIncidentMapper.selectById(incidentId);
		mcsIncident.setStatus(status);

		mcsIncidentMapper.updateById(mcsIncident);

		return residentTransferUpdateStatusResponse;
	}

	@Override
	public List<List<String>> queryImages(String incidentId) {

		QueryWrapper<McsIncident> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.eq("incident_id", incidentId);
		List<McsIncident> list2 = mcsIncidentMapper.selectList(queryWrapper2);
		String applyNo = list2.get(0).getApplyNo();

		List<List<String>> allSrc = new ArrayList<>();
		for (int x = 0; x < imgType.length; x++) {
			List<String> src = new ArrayList<String>();
			QueryWrapper<McsUserImageDetail> queWrapper = new QueryWrapper<McsUserImageDetail>();
			queWrapper.eq("apply_no", applyNo);
			queWrapper.eq("cert_type", imgType[x]);
			List<McsUserImageDetail> selectList = imageDetailMapper.selectList(queWrapper);
			if (selectList == null || selectList.size() == 0) {
				allSrc.add(src);
			} else {
				String userImageId = selectList.get(0).getUserImageId();
				if (userImageId != null && !"".equals(userImageId)) {
					for (McsUserImageDetail image : selectList) {
						src.add(image.getWebUri());
					}
					allSrc.add(src);
					System.out.println("==========================================");
					allSrc.stream().forEach(System.out::println);
				}
			}
		}

		return allSrc;
	}
}
