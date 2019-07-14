package com.lr.oa.oa.service;

import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author lr
 * @date: 2019/7/8
 * Description: No Description
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> servletROle();

    /**
     * 根据主键查找
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 修改角色
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 解绑用户
     * @param roleId
     * @param userIds
     */
    void unBindUser(long roleId, String userIds);

    List<Role> selectByUserId(String userId);
}
