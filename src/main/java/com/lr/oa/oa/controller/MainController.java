package com.lr.oa.oa.controller;

import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.service.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    @Resource
    private IdentityService identityService;

    /**
     *  需求：控制主页面左侧菜单栏以及页面中按钮的显示与隐藏
     *  1、根据用户账号查询用户与角色的中间表获取该用户拥有哪些角色
     *  2、根据角色id查询权限表获取用户拥有哪些二级模块的操作权限，那么这些对应的二级模块应该展示给用户看
     *  3、再根据角色id查询权限表获取用户拥有哪些操作（第三级模块）
     * @return
     */
    @RequestMapping("main")
    public String main(WebRequest request){

        Map<Module, List<Module>> maps = identityService.findLeftMenuOperas();
        request.setAttribute("menuOperas",maps,WebRequest.SCOPE_SESSION);

        //控制页面中的按钮（增加、删除、修改、查询、预览）的显示与隐藏

      List<String> pageOperas=  identityService.findPageOperasByUserId();
       request.setAttribute("pageOperas",pageOperas,WebRequest.SCOPE_SESSION);
        return "main";

    }
}
