package com.daoImpl;

import com.beans.User;
import com.dao.AdminUserDao;
import com.forms.UserForm;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihe on 16-5-26.
 */
public class AdminUserDaoImpl extends HibernateDaoSupport implements AdminUserDao {
    @Override
    public void deleteUser(int id) {
        String sqlDelete="delete from User where id=?";
        String sqlDelete1="delete from Album where userId=?";
        try {
            Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
            Session session1=getHibernateTemplate().getSessionFactory().getCurrentSession();
            Query q=session.createQuery(sqlDelete);
            q.setParameter(0,id);
            q.executeUpdate();
            Query q1=session1.createQuery(sqlDelete1);
            q1.setParameter(0,id);
            q1.executeUpdate();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<UserForm> userPage(String hql, int start, int len) {
        List<User> list;
        List<UserForm> userFormList=new ArrayList<>();
        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Query q = session.createQuery(hql);
            q.setFirstResult(start);
            q.setMaxResults(len);
            list = q.list();
            for(int i=0;i<list.size();i++){
                UserForm userForm=new UserForm();
                BeanUtils.copyProperties(list.get(i),userForm);
                userFormList.add(i,userForm);
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return userFormList;
    }
}
