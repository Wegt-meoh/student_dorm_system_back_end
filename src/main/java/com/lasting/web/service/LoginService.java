package com.lasting.web.service;

import javax.annotation.Resource;

import com.lasting.exception.ServiceException;
import com.lasting.exception.UserPasswordNotMatchException;
import com.lasting.constant.Constants;
import com.lasting.entity.model.LoginUser;
import com.lasting.entity.SysUser;
import com.lasting.manager.AsyncManager;
import com.lasting.manager.factory.AsyncFactory;
import com.lasting.service.ISysUserService;
import com.lasting.utils.common.core.text.DateUtils;
import com.lasting.utils.common.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class LoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private ISysUserService userService;


    /**
     * 登录验证
     *
     * @param studentNumber 用户名
     * @param password 密码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String studentNumber, String password, String uuid)
    {
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(studentNumber, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(studentNumber, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(studentNumber, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(studentNumber, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser user=new SysUser();
        user.setUserId(userId);
        user.setCreateTime(DateUtils.getNowDate());
        userService.updateUserProfile(user);
    }
}
