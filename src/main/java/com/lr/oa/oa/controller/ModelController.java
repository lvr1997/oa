package com.lr.oa.oa.controller;

import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.utils.PageModel;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lr
 */
@Controller
@RequestMapping("/identity/module")
public class ModelController {

    @Resource
    private IdentityService identityService;

    @RequestMapping("/mgrModule")
    public String  mgrModule(){

        return  "/identity/module/mgrModule";
    }

    @ResponseBody
    @RequestMapping("/loadAllModule")
    public String loadAllModule(){

        String result = identityService.loadAllModule();

        return result;
    }

    /**
     * 根据父级模块的code获取子模块信息
     * @param code
     * @param model
     * @return
     */
    @RequestMapping("/getModulesByPcode")
    public String getModulesByPcode(String code,Model model){
        List<Module> module = identityService.getModulesByPcode(code);
        model.addAttribute("modules",module);
        return "/identity/module/module";
    }

}
