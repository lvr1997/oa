package com.lr.oa.oa.service;

import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.utils.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

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

}