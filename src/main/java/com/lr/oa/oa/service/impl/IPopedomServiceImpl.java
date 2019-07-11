package com.lr.oa.oa.service.impl;

import com.lr.oa.oa.dao.PopedomMapper;
import com.lr.oa.oa.dao.RoleMapper;
import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.entity.Popedom;
import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IPopedomService;
import com.lr.oa.oa.service.RoleService;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lr
 * @date: 2019/7/11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IPopedomServiceImpl implements IPopedomService {

    @Resource
    private PopedomMapper popedomMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> findOperaCodeByRoleIdAndModuleCode(Long id, String code) {

        return popedomMapper.findOperaCodeByRoleIdAndModuleCode(id, code);
    }

    @Override
    public void bindOpera(String code, String codes, Long id) {
       //将之前绑定好的权限信息删除
        try {
            popedomMapper.deleteBindOpera(code,id);
            if(codes != null && !codes.equals("")) {

                String[] mCodes = codes.split(",");

                for(String thirdCode : mCodes) {
                    Popedom popedom = new Popedom();
                    //设置角色信息，相当于该权限属于哪一个角色
                    popedom.setRoleId(id);
                    //设置二级模块信息，相当于该权限属于哪一个二级模块
                    popedom.setModuleCode(code);

                    //设置创建时间
                    popedom.setCreateDate(new Date());
                    //设置创建人
                    popedom.setCreater(((User)ConstantUtils.getSession().getAttribute(ConstantUtils.SESSION_USER)).getUserId());

                    //设置操作|三级模块信息
                    popedom.setOperaCode(thirdCode);

                    popedomMapper.insertSelective(popedom);
                }
            }

        } catch (Exception e) {
           throw new RuntimeException();
        }


    }

}

