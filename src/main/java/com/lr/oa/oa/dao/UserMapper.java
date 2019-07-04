package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User  selectByUserAndPass(@Param("userId") String userId, @Param("passWord") String passWord);

    List<User> selectUser(User user);

    long countUsers(User user);
}