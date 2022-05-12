package com.lasting.service;

import com.lasting.entity.SysRole;

import java.util.List;

public interface ISysRoleService {
    public SysRole selectRoleByUserId(Long userId);
    public List<SysRole> selectRoleAll();
    public SysRole selectRoleByRoleId(Long roleId);
    public String getRoleKeyByUserId(Long userId);
}
