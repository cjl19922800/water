package com.shanghaiwater.mcs.admin.dialect;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import com.shanghaiwater.mcs.admin.service.impl.SysMultiCodeDetailServiceImpl;
import com.shanghaiwater.mcs.db.entity.SysMultiCodeDetail;

public class XzqNameTagProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "xzq";
	private static final int PRECEDENCE = 10000;

	
	public XzqNameTagProcessor(String dialectPrefix) {
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
		SysMultiCodeDetailServiceImpl dictService = applicationContext.getBean(SysMultiCodeDetailServiceImpl.class);
		String dictVaue = tag.getAttributeValue("value");
		SysMultiCodeDetail xzqDetail = dictService.getDetailByItemCode(dictVaue);
		String xzqName = "";
		if(null != xzqDetail) {
			xzqName = xzqDetail.getItemValue();
		}
		IModelFactory modelFactory = context.getModelFactory();
        IModel model = modelFactory.createModel();
        model.add(modelFactory.createOpenElementTag("span"));
        model.add(modelFactory.createText(xzqName));
        model.add(modelFactory.createCloseElementTag("span"));
        structureHandler.replaceWith(model, false);
	}
	
	
}
