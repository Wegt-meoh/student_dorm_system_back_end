package com.lasting.entity.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.lasting.entity.SysRole;
import com.lasting.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUser implements UserDetails{
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String token;
    private Long loginTime;
    private Long expireTime;
    private String ipAddress;
    private SysUser user;

    public String getRoleKey(){return this.user.getRoleKey();}

    public LoginUser(Long userId,SysUser user) {
        this.userId=userId;
        this.user = user;
    }

    public LoginUser(Long userId, String token, Long loginTime, Long expireTime, String ipAddress, SysUser user) {
        this.userId = userId;
        this.token = token;
        this.loginTime = loginTime;
        this.expireTime = expireTime;
        this.ipAddress = ipAddress;
        this.user = user;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public SysUser getUser() {
        return this.user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getStudentNumber(){return this.user.getStudentNumber();}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getStudentNumber();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
