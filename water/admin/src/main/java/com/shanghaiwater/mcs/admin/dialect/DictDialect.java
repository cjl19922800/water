package com.shanghaiwater.mcs.admin.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class DictDialect extends AbstractProcessorDialect {

	private static final String DIALECT_NAME = "Sys Dialect";
	
	public DictDialect() {
        // 设置自定义方言与“方言处理器”优先级相同
        super(DIALECT_NAME, "sys", StandardDialect.PROCESSOR_PRECEDENCE);
    }
	
	
	@Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<IProcessor>();
        // 添加自定义标签
        processors.add(new SysDictTagProcessor(dialectPrefix));//select字典框
//        processors.add(new DictTagProcessor(dialectPrefix));
        processors.add(new DictNameTagProcessor(dialectPrefix));//字典code转name
        processors.add(new XzqNameTagProcessor(dialectPrefix));//选择区/街道转name
        processors.add(new PermissionTagProcessor(dialectPrefix));//权限按钮是否显示
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}
