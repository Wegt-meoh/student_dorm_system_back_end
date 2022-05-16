package com.lasting.web.controller;

import com.lasting.constant.Constants;
import com.lasting.entity.SysUser;
import com.lasting.entity.model.AjaxResult;
import com.lasting.entity.model.LoginBody;
import com.lasting.service.ISysRoleService;
import com.lasting.web.service.LoginService;
import com.lasting.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class LoginController
{
    @Autowired
    private LoginService loginService;


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getStudentNumber(), loginBody.getPassword(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @PreAuthorize("@ss.hasPermi('student')")
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user=SecurityUtils.getLoginUser().getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG, user);
        return ajax;
    }
}
