package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.Job;

public interface JobMapper {
    int deleteByPrimaryKey(String code);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}