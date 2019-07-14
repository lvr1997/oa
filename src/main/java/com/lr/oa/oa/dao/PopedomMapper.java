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
     * 删除绑定的操作
     * @param code
     * @param id
     */
    void deleteBindOpera(@Param("code") String code,@Param("id") Long id);

    /**
     * 左侧菜单栏展示权限
     * @param userId
     * @return
     */
    List<String> findLeftMenuOperas(String userId);

    /**
     * //控制页面中的按钮（增加、删除、修改、查询、预览）的显示与隐藏
     * @param userId
     * @return
     */
    List<String> findPageOperasByUserId(String userId);
}