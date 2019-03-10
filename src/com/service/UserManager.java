package com.service;

import com.forms.LoginForm;
import com.forms.UserForm;

/**
 * 参数为form类或者基本类型
 *
 */
public interface UserManager {
    //注册
    public boolean registerManager(UserForm userFrom);
    //登录
    public LoginForm loginManager(LoginForm loginForm);
    //获取用户数据
    public UserForm showInfoManager(int id);
    //更新用户基本数据
    public boolean updateManager(UserForm userForm);
    //更新用户密码
    public boolean updatePwdManager(LoginForm loginForm);
    //保存用户个人主页主题
    public boolean saveTheme(String url,String userid);
    //查找用户个人主页主题
    public String findTheme(String userid);
    //更新用户个人主页主题
    public boolean updateTheme(String url,String userid);
    //删除用户个人主页主题
    public boolean deleteTheme(String userid);
    //所有用户数量
    public long allUserNum();
}
