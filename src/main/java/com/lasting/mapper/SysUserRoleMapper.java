package com.lasting.mapper;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    public int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
    public int deleteUserRoleByUserId(Long userId);
}
