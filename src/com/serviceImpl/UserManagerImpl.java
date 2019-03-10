package com.serviceImpl;

import com.dao.UserDao;
import com.forms.LoginForm;
import com.forms.UserForm;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import com.service.UserManager;
import com.beans.*;

/**
 * 接收action类传送过来的参数
 *
 */
public class UserManagerImpl implements UserManager{
    private UserDao userDao;
    private User user=new User();

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean registerManager(UserForm userForm) throws HibernateException{
        BeanUtils.copyProperties(userForm,user);
        user.setUserAlbumNum(0);
        return  userDao.saveUser(user);
    }

    @Override
    public LoginForm loginManager(LoginForm loginForm) throws HibernateException{
        BeanUtils.copyProperties(loginForm,user);
        return userDao.findUser(user);
    }

    @Override
    public UserForm showInfoManager(int id) throws HibernateException{
        return userDao.findInfo(id);
    }

    @Override
    public boolean updateManager(UserForm userForm) {
        BeanUtils.copyProperties(userForm,user);
        if(userDao.updateInfo(user)){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePwdManager(LoginForm loginForm) {
        BeanUtils.copyProperties(loginForm,user);
        if(userDao.updatePwdInfo(user)){
            return true;
        }
        return false;
    }

    @Override
    public boolean saveTheme(String url, String userid) {
        return userDao.saveTheme(url,userid);
    }

    @Override
    public String findTheme(String userid) {
        return userDao.findTheme(userid);
    }

    @Override
    public boolean updateTheme(String url, String userid) {
        return userDao.modifyTheme(url,userid);
    }

    @Override
    public boolean deleteTheme(String userid) {
        return userDao.deleteTheme(userid);
    }

    @Override
    public long allUserNum() {
        return userDao.allUserNum();
    }
}
