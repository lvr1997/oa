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

    /**
     * 根据父模块查找子模块
     * @param code
     * @param codeLength
     * @return
     */
    List<Module> getModulesByPcode(@Param("code") String code, @Param("codeLength")Integer codeLength);

    /**
     * 加载三级模块
     * @param code
     * @param i
     * @return
     */
    List<Module> loadThirdModule( @Param("code") String code ,@Param("i") int i );


}