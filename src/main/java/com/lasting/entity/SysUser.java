package com.lasting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasting.utils.common.xss.Xss;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class SysUser {
    private Long userId;
    private String username;
    private String password;
    private String sex;
    private String studentNumber;
    private String phoneNumber;
    private Date createTime;
    private Date updateTime;
    private Long roleId;
    private SysRole role;
    private Long dormId;
    private SysDorm dorm;

    public String getRoleKey(){return this.role.getRoleKey();}

    public boolean isAdmin(){
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId){
        return roleId!=null&&1L==roleId;
    }

    public SysUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Xss
    @Size(min = 2,max = 32,message = "用户名在2到32个字符之间")
    @NotBlank
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @JsonProperty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 4, max = 30, message = "用户账号长度在4到30个字符之间")
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public Long getDormId() {
        return dormId;
    }

    public void setDormId(Long dormId) {
        this.dormId = dormId;
    }

    public SysDorm getDorm() {
        return dorm;
    }

    public void setDorm(SysDorm dorm) {
        this.dorm = dorm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
