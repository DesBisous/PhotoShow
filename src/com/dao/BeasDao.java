package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public interface BeasDao {

//  设置SessionFactory
    public void setSessionFactory(SessionFactory sessionFactory);
//  获取SessionFactory
    public Session getSession();
}
