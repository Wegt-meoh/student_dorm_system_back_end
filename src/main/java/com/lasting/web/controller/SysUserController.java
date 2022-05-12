package com.lasting.web.controller;


import com.lasting.entity.SysUser;
import com.lasting.entity.model.AjaxResult;
import com.lasting.service.ISysRoleService;
import com.lasting.service.ISysUserService;
import com.lasting.utils.common.core.page.TableDataInfo;
import com.lasting.utils.common.core.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController{
    @Autowired
    ISysUserService userService;
    @Autowired
    ISysRoleService roleService;

    @PreAuthorize("@ss.hasPermi('common')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user){
        startPage();
        List<SysUser> users=userService.selectUserList(user);
        return getDataTable(users);
    }

    @PreAuthorize("@ss.hasPermi('admin')")
    @GetMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysUser user){
        if(userService.checkStudentNumberUnique(user.getStudentNumber())==false){
            return AjaxResult.error("新增用户'" + user.getStudentNumber() + "'失败，登录学号已存在");
        }else if(StringUtils.isNotEmpty(user.getPhoneNumber())&&userService.checkPhoneNumberUnique(user.getPhoneNumber())==false){
            return AjaxResult.error("新增用户'" + user.getStudentNumber() + "'失败，电话号码已存在");
        }
        return toAjax(userService.insertUser(user));
    }
}
