package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.Dept;

import java.util.List;
import java.util.Map;

public interface DeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Map<String,String>> findAllDept();



}