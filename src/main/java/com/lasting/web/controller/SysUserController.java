package com.lasting.web.controller;

import com.lasting.constant.HttpStatus;
import com.lasting.entity.SysDorm;
import com.lasting.entity.SysRole;
import com.lasting.entity.SysUser;
import com.lasting.entity.model.AjaxResult;
import com.lasting.entity.model.LoginUser;
import com.lasting.security.SecurityUtils;
import com.lasting.service.ISysDormService;
import com.lasting.service.ISysRoleService;
import com.lasting.service.ISysUserService;
import com.lasting.utils.common.ServletUtils;
import com.lasting.utils.common.core.page.TableDataInfo;
import com.lasting.utils.common.core.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("@ss.hasPermi('admin')")
    @DeleteMapping("/{userId}")
    public AjaxResult delete(@PathVariable Long userId){
        Long loginUserId = getUserId();
        if(userService.isSuperAdmin(userId)||loginUserId==userId){
            return AjaxResult.error(HttpStatus.ERROR,"无操作权限");
        }else if(!userService.isSuperAdmin(loginUserId)&&userService.isAdmin(userId)){
            return AjaxResult.error(HttpStatus.ERROR,"无操作权限");
        }
        return toAjax(userService.deleteUser(userId));
    }

    @PreAuthorize("@ss.hasPermi('admin')")
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user){
        Long loginUserId = getUserId();
        if(userService.isSuperAdmin(user)){
            return AjaxResult.error(HttpStatus.ERROR,"无操作权限");
        }else if(user.getUserId()!=loginUserId&&!userService.isSuperAdmin(loginUserId)&&userService.isAdmin(user)){
            return AjaxResult.error(HttpStatus.ERROR,"无操作权限");
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.resetUserPwd(user));
    }

    @PreAuthorize("@ss.hasPermi('admin')")
    @PutMapping
    public AjaxResult update(@RequestBody SysUser user){
        Long loginUserId = getUserId();
        if(userService.isSuperAdmin(user)){
            return AjaxResult.error(HttpStatus.ERROR,"无操作权限");
        }else if(user.getUserId()!=loginUserId&&!userService.isSuperAdmin(loginUserId)&&userService.isAdmin(user)){
            return AjaxResult.error(HttpStatus.ERROR,"无操作权限");
        }else if(user.getPhoneNumber()!=null&&!userService.checkPhoneNumberUnique(user.getPhoneNumber())){
            return AjaxResult.error(HttpStatus.ERROR,"电话号码已存在");
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
        return toAjax(userService.updateUser(user));
    }
}
