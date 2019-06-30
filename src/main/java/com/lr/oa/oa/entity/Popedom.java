package com.lr.oa.oa.entity;

import java.util.Date;

public class Popedom {
    private Long id;

    private Date createDate;

    private String creater;

    private String moduleCode;

    private String operaCode;

    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getOperaCode() {
        return operaCode;
    }

    public void setOperaCode(String operaCode) {
        this.operaCode = operaCode == null ? null : operaCode.trim();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}