package com.lr.oa.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.utils.PageModel;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lr
 */
@Controller
@RequestMapping("/identity")
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


    @RequestMapping("/user/showAddUser")
    public String addUser(){

        return "/identity/user/addUser";
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    public String addUser(User user, Model model){

        user.setCreateDate(new Date());
        user.setCreater((String) ConstantUtils.getSession().getAttribute(ConstantUtils.SESSION_USER));
        int flag = identityService.addUser(user);
        if (flag>0){
            model.addAttribute("message", "添加成功");
        } else {
            model.addAttribute("message", "添加失败");
        }

        return "/identity/user/addUser";
    }

    /**
     * 动态加载部门职位选择框信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deptandjobSelect")
    public String deptandjobSelect(){
        String  result = identityService.deptandJob();
        return  result;
    }

    @RequestMapping("/user/deleteUserByIds")
    public String deleteUserByIds(@RequestParam("userIds") String userIds ,Model model){
        try {
            int flag = identityService.deleteUser(userIds);
            if (flag>0){
                model.addAttribute("message", "删除成功");
            } else {
                model.addAttribute("message", "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "forward:/user/selectUser";
    }


}
