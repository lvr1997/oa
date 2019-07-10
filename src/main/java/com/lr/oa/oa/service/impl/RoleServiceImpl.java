package com.lr.oa.oa.service.impl;

import com.lr.oa.oa.dao.DeptMapper;
import com.lr.oa.oa.dao.JobMapper;
import com.lr.oa.oa.dao.RoleMapper;
import com.lr.oa.oa.entity.Dept;
import com.lr.oa.oa.entity.Job;
import com.lr.oa.oa.entity.Role;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lr
 * Date: 2019/7/8
 * Time: 14:35
 * Description: No Description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {


    @Resource
    private RoleMapper roleMapper;

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        try {
        return   roleMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }



    @Override
    public List<Role> servletROle() {

        return  roleMapper.selectRole();
    }

    @Override
    public Role selectByPrimaryKey(Long id) {

        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void unBindUser(long roleId, String userIds) {

        try {
            String [] ids=userIds.split(",");
            for (String uid :ids) {
                roleMapper.unBindUser(roleId,uid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
