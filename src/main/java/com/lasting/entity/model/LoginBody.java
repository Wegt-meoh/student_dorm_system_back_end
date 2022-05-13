package com.lasting.entity.model;

/**
 * 用户登录对象
 *
 * @author ruoyi
 */
public class LoginBody
{
    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 唯一标识
     */
    private String uuid;

    public String getStudentNumber()
    {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber)
    {
        this.studentNumber = studentNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
