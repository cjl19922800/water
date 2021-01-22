package com.shanghaiwater.mcs.admin.intercept;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shanghaiwater.mcs.db.entity.McsAulog;
import com.shanghaiwater.mcs.db.mapper.McsAulogMapper;

@Component
public class McsOpLogInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private McsAulogMapper mcsAulogMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        McsAulog log = new McsAulog();
        log.setEventid(UUID.randomUUID().toString());
        log.setId(UUID.randomUUID().toString());
        log.setRequestIp(request.getRemoteAddr());
        log.setRequestUri(request.getRequestURI());
        log.setStartTime(new Date());
        mcsAulogMapper.insert(log);
        request.setAttribute("McsAulogId", log.getId());
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		String aulogId = (String)request.getAttribute("McsAulogId");
		QueryWrapper<McsAulog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", aulogId);
		McsAulog log = mcsAulogMapper.selectOne(queryWrapper);
		log.setStatus(response.getStatus()+"");
		log.setEndTime(new Date());
		if(null != ex) {
			log.setResult("1");
		}else {
			log.setResult("0");
		}
		UpdateWrapper<McsAulog> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", aulogId);
		mcsAulogMapper.update(log, updateWrapper);
	}
}
