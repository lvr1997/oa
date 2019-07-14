package com.lr.oa.oa.service;

import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.utils.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lr
 * @date 2019/07/11
 */
public interface IdentityService {

    /**
     * 用户登录
     *
     * @param name
     * @param pass
     * @param vcode
     * @return
     */
    String userLogin(String name, String pass, String vcode);

    /**
     * 分页查询
     *
     * @param user
     * @param pageModel
     * @return
     */
    PageInfo<User> selectUser(User user , PageModel pageModel);

    /**
     * 动态加载部门和职位信息
     *
     * @return
     */
    String deptandJob();

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 删除用户（可批量）
     * @param userIds
     * @return
     */
    int deleteUser(String userIds);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    User selectByPrimaryKey(String userId);

    /**
     * 通过角色id查询用户
     * @param id
     * @return
     */
    List<User> selectUserByRoleId(Long id);

    /**
     * 加载未绑定的用户
     * @param roleId
     * @return
     */
    List<User> showUnbindUser(long roleId);

    /**
     * 绑定用户
     * @param roleId
     * @param ids
     */
    void bindUser(long roleId, String ids);

    /**
     * 加载所有模块
     * @return
     */
    String loadAllModule();

    /**
     * 根据父模块加载所有子模块
     * @param code
     * @return
     */
    List<Module> getModulesByPcode(String code);

    /**
     * 加载三级模块（查询二级模块下都有哪些操作）
     * @param code
     * @return
     */
    List<Module> loadThirdModule(String code);

    /**
     * 查询系统左侧菜单栏
     * @return
     */
    Map<Module,List<Module>> findLeftMenuOperas();

    /**
     * 根据用户id查询用户绑定的操作权限
     * @return
     */
    List<String> findPageOperasByUserId();
}