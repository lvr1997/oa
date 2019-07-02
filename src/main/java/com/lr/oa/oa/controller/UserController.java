package com.lr.oa.oa.controller;

import com.lr.oa.oa.service.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private IdentityService identityService;

    @RequestMapping("/login")
    public String  login(){

        return  "login";
    }

    @ResponseBody
    @RequestMapping("/ajaxLogin")
    public String  loginUser(@RequestParam("userId")String userId,@RequestParam("passWord")String passWord,@RequestParam("vcode")String vcode){
       String result= identityService.userLogin(userId,passWord,vcode);
     return  result;
    }

    @RequestMapping("/user/showInfo")
    public String showUser(){

        return  "home";
    }

}
