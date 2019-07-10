package com.lr.oa.oa.controller;

import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;
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
@RequestMapping("/identity/role")
public class RoleController {

    @Resource
    private IdentityService identityService;
    @Resource
    private RoleService  roleService;

    @RequestMapping("/selectRole")
    public String  selectUser( Model model){
        List<Role> role = roleService.servletROle();
        model.addAttribute("roles", role);
        return   "/identity/role/role";
    }

    /**
     * 展示修改角色页表页面
     * @param model
     * @return
     */
    @RequestMapping("/showUpdateRole")
    public String  showUpdateRole(@RequestParam("roleId") Long roleId ,Model model){
        Role role= roleService.selectByPrimaryKey(roleId);
        model.addAttribute("role",role);
        return  "/identity/role/updateRole";
    }

    /**
     *修改角色信息
     * @param role
     * @param model
     * @return
     */
    @RequestMapping("/updateRole")
    public String  showUpdateRole( Role role ,Model model){
        try {
            role.setModifier((String) ConstantUtils.getSession().getAttribute(ConstantUtils.SESSION_USER));
            role.setModifyDate(new Date());
            int flag = roleService.updateByPrimaryKeySelective(role);

            model.addAttribute("message","修改成功");
        } catch (Exception e) {
            model.addAttribute("message","修改失败");
        }
        return  "/identity/role/updateRole";
    }

    /**
     * 查询角色绑定的用户
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/selectRoleUser")
    public String  selectRoleUser( Long id ,Model model){
        try {
            List<User> users=  identityService.selectUserByRoleId(id);
            Role role = roleService.selectByPrimaryKey(id);
            model.addAttribute("users",users);
            model.addAttribute("role", role);
        } catch (Exception e) {
            model.addAttribute("message", "数据加载失败！");
        }
        return "/identity/role/bindUser/roleUser";
    }

    /**
     * 角色解绑用户
     * @param roleId
     * @param userIds
     * @param model
     * @return
     */
    @RequestMapping("/unBindUser")
    public String  unBindUser( @RequestParam("roleId")long roleId,@RequestParam("userIds")String userIds,Model model){
        try {
            roleService.unBindUser(roleId,userIds);
            model.addAttribute("message","解绑成功");
        } catch (Exception e) {
            model.addAttribute("message","解绑失败");
        }
        return "forward:/identity/role/selectRoleUser?id="+roleId;
    }

    /**
     *获取角色已经绑定的用户信息
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("/showUnbindUser")
    public String  showUnbindUser(@RequestParam("roleId")long roleId,Model model){
        try {
            List<User> users=   identityService.showUnbindUser(roleId);
            Role role = roleService.selectByPrimaryKey(roleId);
            model.addAttribute("role", role);
            model.addAttribute("users",users);

        } catch (Exception e) {

        }
        return "/identity/role/bindUser/bindUser";
    }

    @RequestMapping("/bindUser")
    public String  bindUser(@RequestParam("roleId")long roleId,Model model,String userIds){
        try {
              identityService.bindUser(roleId,userIds);
            model.addAttribute("message","绑定成功");
        } catch (Exception e) {
            model.addAttribute("message","绑定失败");

        }
        return "forward:/identity/role/showUnbindUser";
    }

}
