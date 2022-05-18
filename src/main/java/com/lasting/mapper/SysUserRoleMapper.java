package com.lasting.mapper;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    public int insertUserRole(Long userId, Long roleId);
    public int deleteUserRoleByUserId(Long userId);
}
