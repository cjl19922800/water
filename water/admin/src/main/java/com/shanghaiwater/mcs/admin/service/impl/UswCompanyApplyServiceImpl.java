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
import com.shanghaiwater.mcs.db.entity.UswCompanyApply;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.McsUserImageDetailMapper;
import com.shanghaiwater.mcs.db.mapper.UswCompanyApplyMapper;
import com.shanghaiwater.mcs.admin.model.CompanyApplyListResponse;
import com.shanghaiwater.mcs.admin.model.CompanyApplyUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.service.UswCompanyApplyService;
import com.shanghaiwater.mcs.admin.service.inf.CisCompanyCodeService;
import com.shanghaiwater.mcs.admin.service.inf.ReginCodeService;
import com.shanghaiwater.mcs.db.vo.CompanyApplyMgtVO;
import com.shanghaiwater.mcs.common.vo.DictItem;
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
public class UswCompanyApplyServiceImpl extends ServiceImpl<UswCompanyApplyMapper, UswCompanyApply>
		implements UswCompanyApplyService {

	@Autowired
	private UswCompanyApplyMapper companyApplyWaterMapper;

	@Autowired
	private McsUserImageDetailMapper imageDetailMapper;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;

	@Autowired
	private ReginCodeService reginCodeService;

	@Autowired
	private CisCompanyCodeService cisCompanyCodeService;

	private final static String[] imgType = { "单位接水申请书（盖章）", "营业执照", "产权证", "项目批文", "接水用地及附近的地形图", "建筑工程规划许可证",
			"建筑工程施工许可证", "施工图审查合格书", "消防给水批复", "设计文件" };

	@Override
	public CompanyApplyListResponse queryList(Map<String, Object> map) {
		CompanyApplyListResponse result = new CompanyApplyListResponse();

		Integer count = companyApplyWaterMapper.selectCompanyApplyCount(map);
		List<CompanyApplyMgtVO> list2 = companyApplyWaterMapper.selectCompanyApplyList(map);
		list2.forEach(l -> {
			List<WaterCompany> itemCode = null; // TODO
												// cisCompanyCodeService.selectCisCompanyByCompanyCode(l.getCisCompany());
			String code = itemCode.get(0).getName();
			l.setCisCompany(code);
			List<DictItem> itemCode2 = reginCodeService.selectDictByItemCode("", l.getApplyContent()); // TODO
			String code2 = itemCode2.get(0).getItemValue();
			l.setApplyContent(code2);
		});

		result.setData(list2);

		result.setCode(0);
		result.setCount(count + "");
		return result;
	}

	@Override
	public CompanyApplyUpdateStatusResponse updateStatus(String incidentId, String status) {
		CompanyApplyUpdateStatusResponse companyApplyUpdateStatusResponse = new CompanyApplyUpdateStatusResponse();

		McsIncident mcsIncident = mcsIncidentMapper.selectById(incidentId);
		mcsIncident.setStatus(status);

		mcsIncidentMapper.updateById(mcsIncident);

		return companyApplyUpdateStatusResponse;
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
