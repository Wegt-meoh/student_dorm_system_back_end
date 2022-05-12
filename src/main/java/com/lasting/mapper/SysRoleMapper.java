package com.lasting.mapper;

import com.lasting.entity.SysRole;

import java.util.List;

public interface SysRoleMapper {
    public SysRole selectRoleByUserId(Long userId);
    public List<SysRole> selectRoleAll();
    public SysRole selectRoleByRoleId(Long roleId);
}
