package com.shanghaiwater.mcs.admin.dialect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.mapper.SysDictItemMapper;

public class DictTagProcessor extends AbstractElementTagProcessor{

	private static final String TAG_NAME = "dict";
	private static final int PRECEDENCE = 10000;
	
	public DictTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML,
                dialectPrefix,
                TAG_NAME,
                true,
                null,
                false, 
                PRECEDENCE
        );
    }

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(context);
        //获取字典service的bean
        SysDictItemMapper dictService = applicationContext.getBean(SysDictItemMapper.class);
        
        //获取标签的属性值
        String dictType = tag.getAttributeValue("type");
        String dictVaue = tag.getAttributeValue("value");
        String type = tag.getAttributeValue("query");
        
        if("1".equals(type)) {
        	Map<String,Object> paraMap = new HashMap<String,Object>();
            paraMap.put("dictCode", dictType);
            paraMap.put("itemCode", dictVaue);
            List<DictItem> dictList = dictService.queryAllItemByCode(paraMap);
            String dictItemName = "";
    		if(dictList.size()>0) {
    			dictItemName = dictList.get(0).getItemValue();
    		}
    		IModelFactory modelFactory = context.getModelFactory();
            IModel model = modelFactory.createModel();
            model.add(modelFactory.createOpenElementTag("div"));
            model.add(modelFactory.createText(dictItemName));
            model.add(modelFactory.createCloseElementTag("div"));
            structureHandler.replaceWith(model, false);
        }else if("2".equals(type)) {
        	String companyName = "";
        	IMgtWaterCompanyService companyService = applicationContext.getBean(IMgtWaterCompanyService.class);
        	List<MgtWaterCompany> companys = companyService.findByCompanyCode(dictVaue);
        	if(companys.size()>0) {
        		companyName = companys.get(0).getName();
    		}
    		IModelFactory modelFactory = context.getModelFactory();
            IModel model = modelFactory.createModel();
            model.add(modelFactory.createOpenElementTag("div"));
            model.add(modelFactory.createText(companyName));
            model.add(modelFactory.createCloseElementTag("div"));
            structureHandler.replaceWith(model, false);
        }else {
        	IModelFactory modelFactory = context.getModelFactory();
            IModel model = modelFactory.createModel();
            model.add(modelFactory.createOpenElementTag("div"));
            model.add(modelFactory.createCloseElementTag("div"));
            structureHandler.replaceWith(model, false);
        }
        
        
	}
	
	
	
	
	
	
	
	
	
}
