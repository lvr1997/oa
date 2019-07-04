package com.lr.oa.oa.controller;

import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.utils.PageModel;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author lr
 */
@Controller
public class UserController {

    @Resource
    private IdentityService identityService;

    @RequestMapping("/login")
    public String  login(){
        ConstantUtils.getSession().invalidate();
        return  "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String  loginUser(@RequestParam("userId")String userId,@RequestParam("passWord")String passWord,@RequestParam("vcode")String vcode){
        String result= identityService.userLogin(userId,passWord,vcode);
        return  result;
    }

    @RequestMapping("/user/showInfo")
    public String showUser(){

        return  "home";
    }

    @RequestMapping("/user/selectUser")
    public String  selectUser(User user, PageModel pageModel, Model model){
        PageInfo<User> userPageInfo = identityService.selectUser(user, pageModel);
        model.addAttribute("users", userPageInfo);
        model.addAttribute("user",user);
        return  "/identity/user/user";
    }

    /**
     * 动态加载部门职位选择框信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deptandjobSelect")
    public String deptandjobSelect(){
        String  result=   identityService.deptandJob();
        return  result;
    }


}
