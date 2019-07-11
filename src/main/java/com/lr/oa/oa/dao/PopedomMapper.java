package com.lr.oa.oa.dao;

import com.lr.oa.oa.entity.Popedom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PopedomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Popedom record);

    int insertSelective(Popedom record);

    Popedom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Popedom record);

    int updateByPrimaryKey(Popedom record);

    /**
     * 根据二级模块code和角色id查询当前角色已绑定的操作code
     * @param id
     * @param code
     * @return
     */
    List<String> findOperaCodeByRoleIdAndModuleCode(@Param("id") Long id, @Param("code") String code);



    /**
     *
     * @param code
     * @param id
     */
    void deleteBindOpera(@Param("code") String code,@Param("id") Long id);
}