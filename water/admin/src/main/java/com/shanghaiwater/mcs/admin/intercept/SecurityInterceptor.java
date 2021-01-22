package com.shanghaiwater.mcs.admin.intercept;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.db.entity.McsAulog;
import com.shanghaiwater.mcs.db.mapper.McsAulogMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {

	@Autowired
	private McsAulogMapper mcsAulogMapper;
	
	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 AdminLoginInfo adminLoginInfo2 = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		 McsAulog mcslog = new McsAulog();
		 mcslog.setEventid(UUID.randomUUID().toString());
		 mcslog.setId(UUID.randomUUID().toString());
		 mcslog.setRequestIp(request.getRemoteAddr());
		 mcslog.setRequestUri(request.getServletPath());
		 mcslog.setResult("0");
		 mcslog.setStartTime(new Date());
		 mcsAulogMapper.insert(mcslog);
		 request.setAttribute("McsAulogId", mcslog.getId());
		 
		 if(handler instanceof HandlerMethod) {
			 HandlerMethod handlerMethod = (HandlerMethod) handler;
			 Method method = handlerMethod.getMethod();
			 boolean isView = method.getReturnType().equals(ModelAndView.class);
		     request.setAttribute("method_return_is_view",isView);
			 PermissionIntercept permission = ((HandlerMethod) handler).getMethodAnnotation(PermissionIntercept.class);
			 if (permission == null) {
				 permission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(PermissionIntercept.class);
			 }
			 if(permission == null) {//无权限验证的
				 return true;
			 }else {
				 HttpSession session = request.getSession(); 
				 if(session == null) {
					 response.getWriter().write("<script>windows.location.href='/admin'</script>");
					 return false;
				 }
				//获取登陆人信息
				 AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
				 if(null == adminLoginInfo) {
					 response.getWriter().write("<script>windows.location.href='/admin'</script>");
					 return false;
				 }
				 List<String> urls = adminLoginInfo.getAuthorities();//获取登陆人url权限
				 String reqPath = request.getServletPath(); //响应的url
				
				 
				 log.info("request url=[{}]", reqPath);
				 if(urls.contains(reqPath)) {
					 log.info("权限验证通过");
					 return true;
				 }else {
					 log.info("权限验证失败");
					 //无此权限
					 throw new MCSException("403","您无此功能权限，请联系管理员");
				 }
			 }
		 }
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
