package com.serviceImpl;

import com.dao.AdminUserDao;
import com.forms.UserForm;
import com.service.AdminUserManager;

import java.util.List;

/**
 * Created by ruihe on 16-5-26.
 */
public class AdminUserManagerImpl implements AdminUserManager {
    private AdminUserDao adminUserDao;

    public AdminUserDao getAdminUserDao() {
        return adminUserDao;
    }

    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    @Override
    public void deleteUser(int id) {
        adminUserDao.deleteUser(id);
    }

    @Override
    public List<UserForm> userPage(String hql, int start, int len) {
        return adminUserDao.userPage(hql,start,len);
    }
}
