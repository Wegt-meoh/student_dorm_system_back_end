package com.lasting.web.service;

import com.lasting.exception.ServiceException;
import com.lasting.entity.SysUser;
import com.lasting.entity.model.LoginUser;
import com.lasting.service.ISysUserService;
import com.lasting.utils.common.core.text.StringUtils;
import com.lasting.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String studentNumber) throws UsernameNotFoundException
    {
        SqlUtil.filterKeyword(studentNumber);
        SysUser user = userService.selectUserByStudentNumber(studentNumber);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", studentNumber);
            throw new ServiceException("登录用户：" + studentNumber + " 不存在");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getUserId(),user);
    }
}
