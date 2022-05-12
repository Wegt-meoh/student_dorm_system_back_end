package com.lasting.utils;

import com.lasting.entity.model.LoginUser;
import com.lasting.security.SecurityUtils;
import com.lasting.utils.common.core.text.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service("ss")
public class PermissionService {
//    private static final String ALL_PERMISSION = "*:*:*";

    public boolean hasPermi(String roleKey)
    {
        if (StringUtils.isEmpty(roleKey))
        {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNull(loginUser) || StringUtils.isEmpty(loginUser.getRoleKey()))
        {
            return false;
        }
        return hasPermissions(loginUser.getRoleKey(), roleKey);
    }

    private boolean hasPermissions(String userRoleKey, String roleKey)
    {
        if(userRoleKey=="admin") return true;
        else return userRoleKey==roleKey;
    }
}
