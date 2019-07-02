package com.lr.oa.oa.service.impl;

import com.lr.oa.oa.dao.UserMapper;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class IdentityServiceimpl implements IdentityService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String userLogin(String userId, String pass, String vcode) {
       HttpSession session=ConstantUtils.getSession();
       String sessionCode=(String) session.getAttribute("verify_code");
       if(!sessionCode.equals(vcode)){
           return "你输入的验证码不正确";
       }else{
           User user=  userMapper.selectByUserAndPass(userId,pass);
           if(user==null){
               return "你输入的账号不正确";
           }else if(!user.getPassWord().equals(pass)){
               return "你输入的密码不正确";

           }
           session.setAttribute(ConstantUtils.SESSION_USER, user);
       }

        return null;
    }
}
