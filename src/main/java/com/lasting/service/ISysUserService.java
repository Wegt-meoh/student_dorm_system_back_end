package com.lasting.service;

import com.lasting.entity.SysDorm;
import com.lasting.entity.SysUser;

import java.util.List;

public interface ISysUserService {
    /**
     * 根据条件分页查询
     * @param user 查询条件
     * @return 用户列表
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     *
     * @param username 用户名
     * @return 用户列表
     */
    public List<SysUser> selectUserListByUsername(String username);

    /**
     * 根据用户id查询指定用户
     * @param userId 用户id
     * @return 用户
     */
    public SysUser selectUserByUserId(Long userId);

    /**
     * 根据学号查询指定用户
     * @param studentNumber 学号
     * @return 用户
     */
    public SysUser selectUserByStudentNumber(String studentNumber);

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return 'admin' or 'student'
     */
    public String selectUserRole(Long userId);

    /**
     * 查询学号是否已存在
     * @param studentNumber
     * @return 已存在-true
     */
    public boolean checkStudentNumberUnique(String studentNumber);
    /**
     * 查询手机号是否已存在
     * @param phoneNumber
     * @return 已存在-true
     */
    public boolean checkPhoneNumberUnique(String phoneNumber);

    /**
     * 查询用户所在宿舍信息
     * @param userId
     * @return SysDorm
     */
    public SysDorm selectUserDorm(Long userId);

    /**
     * 新增用户
     * @param user
     * @return int
     */
    public int insertUser(SysUser user);

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean registerUser(SysUser user);

    /**
     * 新增用户角色
     * @param userId
     * @param roleId
     */
    public void insertUserRole(Long userId,Long roleId);

    /**
     * 新增用户寝室信息
     * @param userId
     * @param dormId
     */
    public void insertUserDorm(Long userId,Long dormId);

    /**
     * 更新用户信息
     * @param user
     * @return int
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户基本信息不涉及其他表
     * @param user
     * @return int
     */
    public int updateUserProfile(SysUser user);

    /**
     * 重置用户密码
     * @param user
     * @return int
     */
    public int resetUserPwd(SysUser user);

    /**
     * 根据id删除指定用户
     * @param userId
     * @return int
     */
    public int deleteUser(Long userId);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    public int deleteUserByIds(Long[] ids);
}
