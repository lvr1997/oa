package com.lr.oa.oa.controller;

import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IPopedomService;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.service.RoleService;
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

    @Resource
    private RoleService roleService;

    @Resource
    private IPopedomService popedomService;

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

    /**
     * 加载三级模块
     *
     * @param code  二级模块code
     * @param module  二级模块名称
     * @param model
     * @return
     */
    @RequestMapping("loadThirdModule")
    public String loadThirdModule(@RequestParam("code") String code, @RequestParam("id")Long id, Module module,Model model){
        Role role = roleService.selectByPrimaryKey(id);
        List<Module> modules = identityService.loadThirdModule(code);
        List<String> operaCodes = popedomService.findOperaCodeByRoleIdAndModuleCode(id, code);

        //返回当前要绑定的角色名称
        model.addAttribute("role", role);

        //返回三级模块列表
        model.addAttribute("modules",modules);

        //返回当前操作模块
        model.addAttribute("module", module);

        //返回当前角色已经绑定了哪些操作（根据角色id和二级模块code查询已绑定的操作code）
        model.addAttribute("operaCodes", operaCodes);

        //跳转至角色绑定操作主页面
        return "identity/role/bindPopedom/operas";

    }

}
