package com.lr.oa.oa.config;

import com.lr.oa.oa.shiro.OaShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤配置
 *
 * @author lr
 * @date: 2019/7/13
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //访问的是后端url地址为 /login的接口
        shiroFilterFactoryBean.setLoginUrl("/identity/login");
        //设置登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/identity/user/selectUser");
        //拦截器
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        //配置不会被拦截的链接，顺序判断
        filterChainMap.put("/static/**", "anon");
        filterChainMap.put("/identity/login", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oaRealm());
        return securityManager;
    }

    @Bean
    public OaShiroRealm oaRealm(){

        return new OaShiroRealm();
    }

}
