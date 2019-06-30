package com.lvr.springbootmanger.dao;

import com.lr.oa.oa.entity.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(String code);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}