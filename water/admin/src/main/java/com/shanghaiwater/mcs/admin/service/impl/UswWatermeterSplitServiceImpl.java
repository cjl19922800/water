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
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswWatermeterSplitMapper;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.model.UswWatermeterSplitListResponse;
import com.shanghaiwater.mcs.admin.model.UswWatermeterSplitUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.UswWatermeterSplitService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.db.vo.SplitIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.UswWatermeterSplitMgtVO;
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
public class UswWatermeterSplitServiceImpl extends ServiceImpl<UswWatermeterSplitMapper, UswWatermeterSplit>
		implements UswWatermeterSplitService {

	@Autowired
	private UswWatermeterSplitMapper uswWatermeterSplitMapper;

	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;


	private final static String[] imgType = { "产权证" };

	
	@Override
	public PageListBasicResponse querySplitList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswWatermeterSplitMapper.querySplitCount(map);
		List<SplitIncidentMgtVO> list = uswWatermeterSplitMapper.querySplitList(map);
		for(SplitIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setPhone(SecurityUtil.decryptAES(vo.getPhone()));
			vo.setContactValue(SecurityUtil.decryptAES(vo.getContactValue()));
		}
		
		response.setData(list);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		return response;
	}
	
	
	@Override
	public UswWatermeterSplitListResponse queryList(Map<String, Object> map) {
		UswWatermeterSplitListResponse result = new UswWatermeterSplitListResponse();

		Integer count = uswWatermeterSplitMapper.selectUswWatermeterSplitCount(map);
		List<UswWatermeterSplitMgtVO> list2 = uswWatermeterSplitMapper.selectUswWatermeterSplitList(map);
		list2.forEach(l -> {
			List<WaterCompany> itemCode = null; // cisCompanyCodeService.selectCisCompanyByCompanyCode(l.getCisCompany());
			String code = itemCode.get(0).getName();
			l.setCisCompany(code);
		});
		result.setData(list2);

		result.setCode(0);
		result.setCount(count + "");
		return result;
	}

	@Override
	public UswWatermeterSplitUpdateStatusResponse updateStatus(String incidentId, String status) {
		UswWatermeterSplitUpdateStatusResponse uswWatermeterSplitUpdateStatusResponse = new UswWatermeterSplitUpdateStatusResponse();

		McsIncident mcsIncident = mcsIncidentMapper.selectById(incidentId);
		mcsIncident.setStatus(status);

		mcsIncidentMapper.updateById(mcsIncident);

		return uswWatermeterSplitUpdateStatusResponse;
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
