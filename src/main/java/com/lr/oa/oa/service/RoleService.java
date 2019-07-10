package com.lr.oa.oa.service;

import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/7/8
 * Time: 14:34
 * Description: No Description
 */
public interface RoleService {
    List<Role> servletROle();
    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    void unBindUser(long roleId, String userIds);

}
