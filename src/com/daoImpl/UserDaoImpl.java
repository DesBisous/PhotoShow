package com.daoImpl;

import com.dao.UserDao;
import com.forms.LoginForm;
import com.forms.UserForm;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import com.beans.*;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public boolean saveUser(User user) {
        //设置为读写状态
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().save(user);
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public LoginForm findUser(User user) {
        LoginForm loginSession=new LoginForm();
        String sqlCheck = "from User where userId=? and userPwd=?";
        try {
            ArrayList<User> list= (ArrayList) getHibernateTemplate().find(sqlCheck, user.getUserId(), user.getUserPwd());
            BeanUtils.copyProperties(list.get(0),loginSession);
        }catch (HibernateException e){
            e.printStackTrace();
            loginSession=null;
            return loginSession;
        }
        return loginSession;
    }

    @Override
    public UserForm findInfo(int id) {
        UserForm userInfo=new UserForm();
        String sqlShow="from User where id=?";
        try{
            ArrayList<User> list= (ArrayList<User>) getHibernateTemplate().find(sqlShow,id);
            BeanUtils.copyProperties(list.get(0),userInfo);
            System.out.println(userInfo.getUserId());
        }catch (HibernateException e){
            e.printStackTrace();
            userInfo=null;
            return userInfo;
        }
        return userInfo;
    }

    @Override
    public boolean updateInfo(User user) {
        String sqlUpdate="update User set userName=?,userEmail=?,userPhNum=?,userAddress=? where id=?";
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().bulkUpdate(sqlUpdate,user.getUserName(),user.getUserEmail(),user.getUserPhNum(),user.getUserAddress(),user.getId());
        }catch (HibernateException e){
            return false;
        }
       return true;
    }

    @Override
    public boolean updatePwdInfo(User user) {
        String sqlUpdate="update User set userPwd=? where id=?";
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().bulkUpdate(sqlUpdate,user.getUserPwd(),user.getId());
        }catch (HibernateException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean saveTheme(String url, String userid) {
        Persontheme persontheme = new Persontheme();
        persontheme.setTheme(url);
        persontheme.setUserid(userid);
        getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        try {
            getHibernateTemplate().save(persontheme);
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String findTheme(String userid) {
        Persontheme persontheme=new Persontheme();
        String sqlShow="from Persontheme where userid=?";
        try{
            ArrayList<Persontheme> list= (ArrayList<Persontheme>) getHibernateTemplate().find(sqlShow,userid);
            return list.get(0).getTheme();
        }catch (HibernateException e){
            e.printStackTrace();
            return "null";
        }
    }

    @Override
    public boolean modifyTheme(String url, String userid) {
        String sqlUpdate="update Persontheme set theme=? where userid=?";
        try {
        getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        getHibernateTemplate().bulkUpdate(sqlUpdate,url,userid);
        }catch (HibernateException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTheme(String userid) {
        String sqlUpdate="delete Persontheme where userid=?";
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().delete(sqlUpdate,userid);
        }catch (HibernateException e){
            return false;
        }
        return true;
    }
    @Override
    public long allUserNum() {
        String sqlCheck="select count(*) from User";
        long num=0;
        try {
            List<Long> list= (List<Long>) getHibernateTemplate().find(sqlCheck);
            num=list.get(0);
        }catch (HibernateException e){
            return 0;
        }
        return num;
    }
}
