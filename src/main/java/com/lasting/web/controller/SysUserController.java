package com.lasting.web.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lasting.entity.SysDorm;
import com.lasting.entity.SysRole;
import com.lasting.entity.SysUser;
import com.lasting.entity.model.AjaxResult;
import com.lasting.security.SecurityUtils;
import com.lasting.service.ISysDormService;
import com.lasting.service.ISysRoleService;
import com.lasting.service.ISysUserService;
import com.lasting.utils.common.core.page.TableDataInfo;
import com.lasting.utils.common.core.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController{
    @Autowired
    ISysUserService userService;
    @Autowired
    ISysRoleService roleService;

    @Autowired
    ISysDormService dormService;

    @PreAuthorize("@ss.hasPermi('admin')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user){
        startPage();
        List<SysUser> users=userService.selectUserList(user);
        return getDataTable(users);
    }

    @PreAuthorize("@ss.hasPermi('admin')")
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysUser user){
        if(userService.checkStudentNumberUnique(user.getStudentNumber())==false){
            return AjaxResult.error("新增用户'" + user.getStudentNumber() + "'失败，登录学号已存在");
        }else if(StringUtils.isNotEmpty(user.getPhoneNumber())&&userService.checkPhoneNumberUnique(user.getPhoneNumber())==false){
            return AjaxResult.error("新增用户'" + user.getStudentNumber() + "'失败，电话号码已存在");
        }
        SysRole role = user.getRole();
        if(role!=null){
            Long roleId = roleService.getRoleIdByRoleKey(role.getRoleKey());
            if(roleId==null){
                user.setRole(null);
            }else {
                role.setRoleId(roleId);
                user.setRole(role);
            }
        }
        SysDorm dorm = user.getDorm();
        if(dorm!=null){
            Long dormId=dormService.getDormId(dorm.getDormNumber(),dorm.getBuildingNumber());
            if(dormId==null){
                user.setDorm(null);
            }else{
                dorm.setDormId(dormId);
                user.setDorm(dorm);
            }
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }
}
