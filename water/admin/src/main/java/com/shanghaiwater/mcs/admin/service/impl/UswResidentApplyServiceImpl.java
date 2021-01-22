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
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswResidentApplyMapper;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyListResponse;
import com.shanghaiwater.mcs.admin.model.ResidentApplyUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.UswResidentApplyService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.db.vo.ResidentApplyMgtVO;
import com.shanghaiwater.mcs.db.vo.ResidentWaterIncidentMgtVO;
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
public class UswResidentApplyServiceImpl extends ServiceImpl<UswResidentApplyMapper, UswResidentApply>
		implements UswResidentApplyService {

	@Autowired
	private UswResidentApplyMapper residentApplyWaterMapper;

	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;

	@Autowired
	private CisCompanyCodeService cisCompanyCodeService;

	private final static String[] imgType = { "居民住宅接水申请表", "身份证", "产权证" };

	
	@Override
	public PageListBasicResponse queryWaterList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = residentApplyWaterMapper.selectWaterCount(map);
		List<ResidentWaterIncidentMgtVO> list = residentApplyWaterMapper.queryResidentList(map);
		
		for(ResidentWaterIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setHoueseCertNo(SecurityUtil.decryptAES(vo.getHoueseCertNo()));
			vo.setPhone(SecurityUtil.decryptAES(vo.getPhone()));
			vo.setContactAddress(SecurityUtil.decryptAES(vo.getContactAddress()));
		}
		
		
		response.setData(list);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		return response;
	}
	
	
	
	
	@Override
	public ResidentApplyListResponse queryList(Map<String, Object> map) {
		ResidentApplyListResponse result = new ResidentApplyListResponse();

		Integer count = residentApplyWaterMapper.selectResidentApplyCount(map);
		List<ResidentApplyMgtVO> list2 = residentApplyWaterMapper.selectResidentApplyList(map);
		list2.forEach(l -> {
			List<WaterCompany> itemCode = null;// TODO
												// cisCompanyCodeService.selectCisCompanyByCompanyCode(l.getCisCompany());

			String code = "";
			if (itemCode != null && itemCode.size() > 0) {
				code = itemCode.get(0).getName();
				l.setCisCompany(code);
			}
		});
		result.setData(list2);

		result.setCode(0);
		result.setCount(count + "");
		return result;
	}

	@Override
	public ResidentApplyUpdateStatusResponse updateStatus(String incidentId, String status) {
		ResidentApplyUpdateStatusResponse residentApplyUpdateStatusResponse = new ResidentApplyUpdateStatusResponse();

		McsIncident mcsIncident = mcsIncidentMapper.selectById(incidentId);
		mcsIncident.setStatus(status);

		mcsIncidentMapper.updateById(mcsIncident);

		return residentApplyUpdateStatusResponse;
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
