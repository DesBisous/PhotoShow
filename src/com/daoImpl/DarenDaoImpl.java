package com.daoImpl;

import com.beans.Masterinfo;
import com.dao.DarenDao;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Created by Benson on 2016/5/24.
 */
public class DarenDaoImpl extends HibernateDaoSupport implements DarenDao {
    /**
     * 更新达人信息库
     * @param masterinfo
     * @return
     */
    @Override
    public boolean updateDaren(Masterinfo masterinfo) {
        try{
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().saveOrUpdate(masterinfo);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
