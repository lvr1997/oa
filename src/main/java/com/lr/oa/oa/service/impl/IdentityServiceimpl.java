package com.lr.oa.oa.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lr.oa.oa.dao.*;
import com.lr.oa.oa.entity.Dept;
import com.lr.oa.oa.entity.Job;
import com.lr.oa.oa.entity.Module;
import com.lr.oa.oa.entity.User;
import com.lr.oa.oa.service.IdentityService;
import com.lr.oa.oa.utils.PageModel;
import com.lr.oa.oa.utils.constant.ConstantUtils;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class IdentityServiceimpl implements IdentityService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private JobMapper jobMapper;

    @Resource
    private ModuleMapper moduleMapper;



    @Override
    public String userLogin(String userId, String pass, String vcode) {
        HttpSession session=ConstantUtils.getSession();
        String sessionCode=(String) session.getAttribute("verify_code");
        if(!sessionCode.equals(vcode)){
            return "你输入的验证码不正确";
        }else{
            User user = userMapper.selectByUserAndPass(userId,pass);
            Dept dept = deptMapper.selectByPrimaryKey(user.getDeptId());
            Job job = jobMapper.selectByPrimaryKey(user.getJobCode());
            user.setDept(dept);
            user.setJob(job);
            if(user==null){
                return "你输入的账号不正确";
            }else if(!user.getPassWord().equals(pass)){
                return "你输入的密码不正确";

           }
           session.setAttribute(ConstantUtils.SESSION_USER, user);
       }

        return null;
    }

    @Override
    public PageInfo<User> selectUser(User user, PageModel pageModel) {
        //根据当前页和每页的记录数，调用pagehelp进行分页处理
        PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize());
        //从数据库中查询出list集合
        List<User> userList = userMapper.selectUser(user);
        for (User u : userList) {
            Dept d = deptMapper.selectByPrimaryKey(u.getDeptId());
            Job j = jobMapper.selectByPrimaryKey(u.getJobCode());
            u.setDept(d);
            u.setJob(j);
        }
        //把list集合放到pageInfo
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        //把总条数放到pageInfo里面
        userPageInfo.setTotal(userMapper.countUsers(user));
        return userPageInfo;
    }

    @Override
    public String deptandJob() {
        try{
            List<Map<String ,String>> depts=deptMapper.findAllDept();
            List<Map<String,String>> jobs=jobMapper.findAllJob();

            JSONObject jb= new JSONObject();
            jb.put("depts",depts);
            jb.put("jobs",jobs);

            return  jb.toJSONString();
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    @Override
    public int deleteUser(String userIds) {
        int flag = 0;
        try {
            String[] userId = userIds.split(",");
            for (int i=0; i<userId.length; i++){
                flag = userMapper.deleteUserById(userId[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> selectUserByRoleId(Long id) {

        try {
            List<User> user= userMapper.selectUserByRoleId(id);
            for (User u : user) {
                Dept d = deptMapper.selectByPrimaryKey(u.getDeptId());
                Job j = jobMapper.selectByPrimaryKey(u.getJobCode());
                u.setDept(d);
                u.setJob(j);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<User> showUnbindUser(long roleId) {

        try {
            List<User>  users=  userMapper.showUnbindUser(roleId);
            for(User user:users){
                Dept d = deptMapper.selectByPrimaryKey(user.getDeptId());
                Job j=  jobMapper.selectByPrimaryKey(user.getJobCode());
                user.setDept(d);
                user.setJob(j);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void bindUser(long roleId, String ids) {

        try {
            String[] ides=ids.split(",");

          for(String uid:ides){
             // roleMapper.unBindUser(roleId,uid);
               userMapper.bindUser(roleId,uid);
          }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String loadAllModule() {

        List<Module> modules = moduleMapper.loadAllModule();
        JSONArray arr = new JSONArray();
        for (Module module : modules) {
            JSONObject ob = new JSONObject();
            String code = module.getCode();
            ob.put("id", code);
            ob.put("pid", code.length() == 4 ? "1" : code.substring(0, code.length()-4));
            ob.put("name", module.getName());
            arr.add(ob);
        }
        System.out.println("arr.toString():"+arr.toString());

        return arr.toString();
    }

    @Override
    public List<Module> getModulesByPcode(String code) {

        try {
            if (code == null){
                code = "";
            }
           List<Module> models= moduleMapper.getModulesByPcode(code, code.length()+4);
           return  models;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Module> loadThirdModule(String code) {
        if(code==null){
            code="";

        }
        List<Module> models= moduleMapper.loadThirdModule(code,code.length()+4);
        return models;
    }

}
