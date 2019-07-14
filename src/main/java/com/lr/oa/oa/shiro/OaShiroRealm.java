package com.lr.oa.oa.shiro;

import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 * 实现AuthorizingRealm接口用户用户认证
 *
 * @author lr
 * @date: 2019/7/13
 */
public class OaShiroRealm extends AuthorizingRealm {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RoleService roleService;

    /**
     * 角色权限和对应的权限添加
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userId = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roles= roleService.selectByUserId(userId);
        System.out.println("user::"+userId);

        //查询用户  调用service
        User user = identityService.selectByPrimaryKey(userId);
        //查询权限
        List<String> permissions = identityService.findPageOperasByUserId();
        //添加角色的和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roles){
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
        }
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        //获取用户信息
        String userId = authenticationToken.getPrincipal().toString();
        User user = identityService.selectByPrimaryKey(userId);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userId, user.getPassWord(), getName());
            return simpleAuthenticationInfo;
        }

//        return null;
    }
}
