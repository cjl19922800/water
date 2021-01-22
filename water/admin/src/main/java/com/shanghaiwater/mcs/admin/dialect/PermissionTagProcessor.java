package com.shanghaiwater.mcs.admin.dialect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.db.mapper.SysDictItemMapper;

/**
 * 权限按钮展示
 * @author yd
 */
public class PermissionTagProcessor extends AbstractAttributeTagProcessor{

	
	private static final String TAG_NAME = "permission";
	private static final int PRECEDENCE = 10000;
	

    public PermissionTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML, 
                dialectPrefix,     
                null,             
                false,             
                TAG_NAME,         
                true,              
                PRECEDENCE,       
                true); 
    }

    /**
     * 查看是否有权限
     * 标签例sys:permission="'/right/role/powerSet'"
     * 有此权限block，无则none
     */
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		List<String> urls = adminLoginInfo.getAuthorities();
		IEngineConfiguration configuration = context.getConfiguration();
		IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
	    IStandardExpression expression = parser.parseExpression(context, attributeValue);
	    String position = (String) expression.execute(context);
	    if(urls.contains(position)) {
	    	structureHandler.setAttribute("style",  "display:block");
	    }else {
	    	structureHandler.setAttribute("style",  "display:none");
	    }
	}

	
	
	
	
}
