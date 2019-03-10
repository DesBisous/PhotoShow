package com.service;


import com.forms.UserForm;

import java.util.List;

/**
 * Created by ruihe on 16-5-26.
 */
public interface AdminUserManager {
    public void deleteUser(int id);
    public List<UserForm> userPage(String hql, int start, int len);
}
