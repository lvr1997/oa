package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

     List<Role> selectRole();


    void unBindUser(@Param("roleId") long roleId,@Param("uid") String uid);
}