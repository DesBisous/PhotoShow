package com.dao;

import com.forms.LoginForm;
import com.forms.UserForm;
import com.beans.User;


public interface UserDao {
    public boolean saveUser(User user);
    public LoginForm findUser(User user);
    public UserForm findInfo(int id);
    public boolean updateInfo(User user);
    public boolean updatePwdInfo(User user);
    public boolean saveTheme( String url, String userid );
    public String findTheme( String userid );
    public boolean modifyTheme( String url, String userid );
    public boolean deleteTheme( String userid );
    public long allUserNum();
}
