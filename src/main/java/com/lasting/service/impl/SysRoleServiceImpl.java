package com.lasting.service.impl;

import com.lasting.entity.SysRole;
import com.lasting.mapper.SysRoleMapper;
import com.lasting.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public SysRole selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public SysRole selectRoleByRoleId(Long roleId) {
        return roleMapper.selectRoleByRoleId(roleId);
    }

    @Override
    public String getRoleKeyByUserId(Long userId) {
        SysRole sysRole = roleMapper.selectRoleByUserId(userId);
        if(sysRole==null) return null;
        return sysRole.getRoleKey();
    }

    @Override
    public Long getRoleIdByRoleKey(String roleKey) {
        List<SysRole> sysRoleList = roleMapper.selectRoleAll();
        for(SysRole i : sysRoleList){
            if(i.getRoleKey().equals(roleKey)){
                return i.getRoleId();
            }
        }
        return null;
    }
}
