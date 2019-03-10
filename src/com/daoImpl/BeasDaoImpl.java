package com.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.dao.BeasDao;

public class BeasDaoImpl implements BeasDao{

	private SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
