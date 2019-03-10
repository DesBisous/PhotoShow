package com.daoImpl;

import com.beans.Admin;
import com.dao.AdminDao;
import com.forms.AdminForm;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.ArrayList;


public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
    @Override
    public boolean saveAdmin(Admin admin) {
        try{
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().save(admin);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public AdminForm findAdmin(Admin admin) {
        AdminForm adminForm =new AdminForm();
        String sqlCheck="from Admin where adminid=? and passwd=?";
        try {
            ArrayList<Admin> list = (ArrayList<Admin>) getHibernateTemplate().find(sqlCheck,admin.getAdminid(),admin.getPasswd());
            if( list == null || list.size()<=0 ) return null;
            else BeanUtils.copyProperties(list.get(0),adminForm);
        }catch (HibernateException e){
            e.printStackTrace();
            return adminForm;
        }
        return adminForm;
    }

    @Override
    public AdminForm HqlFindAdmin(String Hql) {
        AdminForm adminForm =new AdminForm();
        try {
            ArrayList<Admin> list = (ArrayList<Admin>) getHibernateTemplate().find(Hql);
            if( list == null || list.size()<=0 ) return null;
            else BeanUtils.copyProperties(list.get(0),adminForm);
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return adminForm;
    }

    @Override
    public ArrayList<AdminForm> HqlFindAdminAll(String Hql){
        AdminForm adminForm;;
        ArrayList<AdminForm> adminForms = new ArrayList<>();
        try {
            ArrayList<Admin> list = (ArrayList<Admin>) getHibernateTemplate().find(Hql);
            if( list == null || list.size()<=0 ) return null;
            else {
                for( int i = 0 ; i < list.size() ; i++ ){
                    adminForm = new AdminForm();
                    BeanUtils.copyProperties(list.get(i),adminForm);
                    adminForms.add(adminForm);
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return adminForms;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        try{
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().clear();
            getHibernateTemplate().update(admin);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
