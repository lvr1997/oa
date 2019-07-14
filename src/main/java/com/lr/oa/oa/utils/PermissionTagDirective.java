package com.lr.oa.oa.utils;

import com.lr.oa.oa.config.annotation.FreemarkerComponent;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 标签类
 *
 * 在实现了接口TemplateDirectiveModel类上使用注解FreemarkerComponent,
 * 那么类会自动注册为freemarker标签,且标签名称为类的name值
 * @author lr
 * @date: 2019/7/13
 */
@FreemarkerComponent("perm")
public class PermissionTagDirective implements TemplateDirectiveModel {


    private final static String URL = "url";

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //获取标签参数值 <@perm url="/test"></@perm>
        String perm = map.get(URL).toString();
        if (!StringUtils.isEmpty(perm)){
            //判断用户是否有权限
            if (SecurityUtils.getSubject().isPermitted(perm)){
                //显示标签内容
                templateDirectiveBody.render(environment.getOut());
            }
        }
    }
}
