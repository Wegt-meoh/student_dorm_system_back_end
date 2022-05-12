package com.lasting.web.service;

import com.lasting.constant.Constants;
import com.lasting.constant.UserConstants;
import com.lasting.entity.SysUser;
import com.lasting.entity.model.RegisterBody;
import com.lasting.manager.AsyncManager;
import com.lasting.manager.factory.AsyncFactory;
import com.lasting.service.ISysUserService;
import com.lasting.utils.common.MessageUtils;
import com.lasting.utils.common.core.text.StringUtils;
import com.lasting.utils.redis.RedisCache;
import com.lasting.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", studentNumber = registerBody.getStudentNumber(), password = registerBody.getPassword(),username=registerBody.getUsername();

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (studentNumber.length() < UserConstants.USERNAME_MIN_LENGTH
                || studentNumber.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (userService.checkStudentNumberUnique(studentNumber)==false)
        {
            msg = "保存用户'" + studentNumber + "'失败，注册账号已存在";
        }
        else
        {
            SysUser sysUser = new SysUser();
            sysUser.setStudentNumber(studentNumber);
            sysUser.setUsername(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER,
                        MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
//    public void validateCaptcha(String username, String code, String uuid)
//    {
//        String verifyKey = Constants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//        if (captcha == null)
//        {
//            throw new CaptchaExpireException();
//        }
//        if (!code.equalsIgnoreCase(captcha))
//        {
//            throw new CaptchaException();
//        }
//    }
}
