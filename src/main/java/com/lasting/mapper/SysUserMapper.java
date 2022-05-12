package com.lasting.mapper;

import com.lasting.entity.SysDorm;
import com.lasting.entity.SysUser;

import java.util.List;

public interface SysUserMapper {
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
     * 查询学号是否已存在
     * @param studentNumber
     * @return 已存在=1,否则=0
     */
    public int checkStudentNumberUnique(String studentNumber);
    /**
     * 查询手机号是否已存在
     * @param phoneNumber
     * @return 已存在=1,否则=0
     */
    public int checkPhoneNumberUnique(String phoneNumber);

    /**
     * 新增用户
     * @param user
     * @return int
     */
    public int insertUser(SysUser user);

    /**
     * 更新用户信息
     * @param user
     * @return int
     */
    public int updateUser(SysUser user);

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
