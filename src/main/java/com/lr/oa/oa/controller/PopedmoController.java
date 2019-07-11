package com.lr.oa.oa.controller;

import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.entity.Popedom;
import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.service.IPopedomService;
import com.lr.oa.oa.service.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/identity/popedom")
public class PopedmoController {

    @Resource
    private IdentityService identityService;
    @Resource
    private IPopedomService   iPopedomService;

    @RequestMapping("mgrPopedom")
    public String mgrPopedom(Role role, Model model){

        model.addAttribute("role", role);

        //跳转至角色绑定操作主页面
        return "identity/role/bindPopedom/mgrPopedom";

    }


    @RequestMapping("bindOpera")
    public String bindOpera(@RequestParam("code")String code,String codes,@RequestParam("id")Long id,Model model){

        try {
            iPopedomService.bindOpera(code,codes,id);
            model.addAttribute("message","绑定成功");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message","绑定失败");
        }

        return "forward:/identity/module/loadThirdModule";

    }

}
