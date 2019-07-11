package com.lr.oa.oa.service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lr
 * @date: 2019/7/11
 */
public interface IPopedomService {

    /**
     * 根据二级模块code和角色id查询当前角色已绑定的操作code
     * @param id 角色id
     * @param code 二级模块code
     * @return
     */
    List<String> findOperaCodeByRoleIdAndModuleCode(Long id, String code);


    void bindOpera(String code, String codes, Long id);
}
