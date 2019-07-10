package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper {
    int deleteByPrimaryKey(String code);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    /**
     * 查询出所有模块
     * @return
     */
    List<Module> loadAllModule();

    List<Module> getModulesByPcode(@Param("code") String code, @Param("codeLength")Integer codeLength);
}