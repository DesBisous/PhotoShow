package com.daoImpl;

import com.beans.Suggestionbox;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import com.dao.MemberDao;

import java.util.List;
import com.beans.Album;

public class MemberDaoImpl extends HibernateDaoSupport implements MemberDao {

    /**
     * 查询所有的记录数
     * @param hql 查询条件
     * @return 总记录数
     */
    @Override
    public int getAllRowCount(String hql) {
        return this.getHibernateTemplate().find(hql).size();
    }
    /**
     * 分页查询
     * @param hql  查询条件
     * @param offset  开始记录
     * @param length  一次查询几条记录
     * @return 查询的记录集合
     */
    @Override
    public List<Album> queryForPage(final String hql, final int offset, final int length) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query q = session.createQuery(hql);
        q.setFirstResult(offset);
        q.setMaxResults(length);
        List<Album> list = q.list();
//        session.close();
        return list;
    }

    @Override
    public List<Suggestionbox> queryForPageSuggest(String hql, int offset, int length) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query q = session.createQuery(hql);
        q.setFirstResult(offset);
        q.setMaxResults(length);
        List<Suggestionbox> list = q.list();
//        session.close();
        return list;
    }
}
