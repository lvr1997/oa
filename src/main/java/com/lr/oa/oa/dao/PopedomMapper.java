package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.Popedom;

public interface PopedomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Popedom record);

    int insertSelective(Popedom record);

    Popedom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Popedom record);

    int updateByPrimaryKey(Popedom record);
}