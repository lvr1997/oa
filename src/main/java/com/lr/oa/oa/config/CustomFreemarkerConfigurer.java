package com.lr.oa.oa.config;

import com.lr.oa.oa.config.annotation.FreemarkerComponent;
import com.lr.oa.oa.utils.PermissionTagDirective;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * 注册标签到freemarker配置
 *
 * @author lr
 * @date: 2019/7/13
 */
@Component
public class CustomFreemarkerConfigurer implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Autowired
    Configuration configuration;

    @Autowired
    PermissionTagDirective permissionTagDirective;


    /**
     * //在项目启动时执行方法
     *
     * @throws IOException
     * @throws TemplateException
     */
    @PostConstruct
    public void setSharedVariable() throws IOException, TemplateException {
        // 根据注解获取bean ,key is bean name ,value is bean object
        Map<String, Object> map = this.applicationContext.getBeansWithAnnotation(FreemarkerComponent.class);
        for (String key : map.keySet()) {
            configuration.setSharedVariable(key, map.get(key));
        }

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 使用标签
     */
    //如果执行body.render(env.getOut()); 标签中的button会显示.否则不会显示
    // 以此来实现按钮级别的权限控制
//<@perm url="/test"> <button type="button" value="按钮"></button></@perm>

}
