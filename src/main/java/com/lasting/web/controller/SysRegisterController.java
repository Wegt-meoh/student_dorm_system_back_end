package com.lasting.web.controller;

import com.lasting.entity.model.AjaxResult;
import com.lasting.entity.model.RegisterBody;
import com.lasting.service.ISysDormService;
import com.lasting.utils.common.core.text.StringUtils;
import com.lasting.web.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 注册验证
 *
 * @author ruoyi
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysDormService dormService;

//    @Autowired
//    private ISysConfigService configService;

    @GetMapping("/register")
    public int register()
    {
//        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
//        {
//            return error("当前系统没有开启注册功能！");
//        }
//        String msg = registerService.register(user);
//        return StringUtils.isEmpty(msg) ? success() : error(msg);
        int count=0;
        for(int i=1;i<20;i++){
            for (int j=100;j<600;j++){
                if(j%100>=30) continue;
                count+=dormService.insertDorm(String.valueOf(j),String.valueOf(i));
            }
        }
        return count;
    }
}
