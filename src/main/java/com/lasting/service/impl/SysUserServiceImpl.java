package com.lasting.service.impl;

import com.lasting.entity.SysDorm;
import com.lasting.entity.SysRole;
import com.lasting.entity.SysUser;
import com.lasting.mapper.SysRoleMapper;
import com.lasting.mapper.SysUserDormMapper;
import com.lasting.mapper.SysUserMapper;
import com.lasting.mapper.SysUserRoleMapper;
import com.lasting.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysUserRoleMapper userRoleMapper;
    @Autowired
    SysUserDormMapper userDormMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public List<SysUser> selectUserListByUsername(String username) {
        return userMapper.selectUserListByUsername(username);
    }

    @Override
    public SysUser selectUserByUserId(Long userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public SysUser selectUserByStudentNumber(String studentNumber) {
        return userMapper.selectUserByStudentNumber(studentNumber);
    }

    @Override
    public String selectUserRole(Long userId) {
        return selectUserByUserId(userId).getRole().getRoleKey();
    }

    @Override
    public boolean checkStudentNumberUnique(String studentNumber) {
        return userMapper.checkStudentNumberUnique(studentNumber)==1?false:true;
    }

    @Override
    public boolean checkPhoneNumberUnique(String phoneNumber) {
        return userMapper.checkPhoneNumberUnique(phoneNumber)==1?false:true;
    }

    @Override
    public SysDorm selectUserDorm(Long userId) {
        return userMapper.selectUserByUserId(userId).getDorm();
    }

    @Override
    @Transactional
    public int insertUser(SysUser user) {
        int row =userMapper.insertUser(user);
        Long userId=user.getUserId();
        if(user.getRole()!=null){
            Long roleId=user.getRole().getRoleId();
            insertUserRole(userId,roleId);
        }
        if(user.getDorm()!=null){
            Long dormId=user.getDorm().getDormId();
            insertUserDorm(userId,dormId);
        }
        return row;
    }

    @Override
    public boolean registerUser(SysUser user)
    {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public void insertUserRole(Long userId, Long roleId) {
        userRoleMapper.insertUserRole(userId,roleId);
    }

    @Override
    public void insertUserDorm(Long userId, Long dormId) {
        userDormMapper.insertUserDorm(userId,dormId);
    }

    @Override
    @Transactional
    public int updateUser(SysUser user) {
        Long userId = user.getUserId();
        Long dormId = user.getDorm().getDormId();
        Long roleId = user.getRole().getRoleId();
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(userId,roleId);
        userDormMapper.deleteUserDormByUserId(userId);
        insertUserDorm(userId,dormId);
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserProfile(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int resetUserPwd(SysUser user) {
        return userMapper.resetUserPwd(user);
    }

    @Override
    @Transactional
    public int deleteUser(Long userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        userDormMapper.deleteUserDormByUserId(userId);
        return userMapper.deleteUser(userId);
    }

    @Override
    @Transactional
    public int deleteUserByIds(Long[] ids) {
        int count=0;
        for(Long id : ids){
            count+=deleteUser(id);
        }
        return count;
    }

    @Override
    public boolean isSuperAdmin(Long userId) {
        return userId!=null&&userId==1L;
    }

    @Override
    public boolean isSuperAdmin(SysUser user) {
        return user!=null&&isSuperAdmin(user.getUserId());
    }

    @Override
    public boolean isAdmin(Long userId) {
        if(userId==null) return false;
        SysRole role = roleMapper.selectRoleByUserId(userId);
        return role!=null&&role.getRoleKey().equals("admin");
    }

    @Override
    public boolean isAdmin(SysUser user) {
        return user!=null&&isAdmin((user.getUserId()));
    }
}
