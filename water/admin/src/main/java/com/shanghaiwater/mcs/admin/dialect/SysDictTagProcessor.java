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

import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.db.mapper.SysDictItemMapper;

/**
 * 字典下拉框标签
 * @author yd
 */
public class SysDictTagProcessor extends AbstractElementTagProcessor  {

	private static final String TAG_NAME = "dict";
	private static final int PRECEDENCE = 10000;

	
	public SysDictTagProcessor(String dialectPrefix) {
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
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
     // 获取 Spring上下文
        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(iTemplateContext);
        //获取字典service的bean
        SysDictItemMapper dictService = applicationContext.getBean(SysDictItemMapper.class);
        //获取标签的属性值
        String dictType = iProcessableElementTag.getAttributeValue("type");
        String id = iProcessableElementTag.getAttributeValue("id");
        String dictName = iProcessableElementTag.getAttributeValue("name");
        String dictClass = iProcessableElementTag.getAttributeValue("class");
        //查询数据库
        //设置参数
        Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("dictCode", dictType);
        List<DictItem> dictList = dictService.queryAllItemByCode(paraMap);
       
        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag(String.format("select name='%s' id='%s'  class='%s'", dictName,id, dictClass)));
        model.add(modelFactory.createOpenElementTag(String.format("option value=''")));
        model.add(modelFactory.createText("全部"));
        model.add(modelFactory.createCloseElementTag("option"));
        for (DictItem dict : dictList) {
            model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", dict.getItemCode())));
            model.add(modelFactory.createText(dict.getItemValue()));
            model.add(modelFactory.createCloseElementTag("option"));
        }
        model.add(modelFactory.createCloseElementTag("select"));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }


}
