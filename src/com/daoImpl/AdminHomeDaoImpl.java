package com.daoImpl;

import com.dao.AdminHomeDao;
import com.forms.UserAndAlbumForm;
import com.forms.UserForm;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruihe on 16-5-22.
 */
public class AdminHomeDaoImpl extends HibernateDaoSupport implements AdminHomeDao {
    @Override
    public long numOfAlbum(String keyWords) {
        String sqlCheck="select count(*) from Album where theme like ?";
        long num=0;
        try{
            List<Long> list= (List<Long>) getHibernateTemplate().find(sqlCheck,keyWords);
            num=list.get(0);
        }catch (HibernateException e){
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    @Override
    public List<UserAndAlbumForm> maxNumOrderBy() {
        String sqlCheck="select new com.forms.UserAndAlbumForm(u.id,b.good,u.userAlbumNum,u.userHeadImg,u.userId,u.userName,b.theme,b.title,b.albumIntroduction)" +
                " from User u , Album b where u.id=b.userId order by b.good desc";
        List<UserAndAlbumForm> userAndAlbumForm=new ArrayList<>();
        try{
            List<UserAndAlbumForm> list= (List<UserAndAlbumForm>) getHibernateTemplate().find(sqlCheck);
            if(list.size()>=3){
                for (int i=0;i<3;i++){
                    UserAndAlbumForm F=new UserAndAlbumForm();
                    BeanUtils.copyProperties(list.get(i),F);
                    userAndAlbumForm.add(i,F);
                }
            }
            if(list.size()<3){
                for (int i=0;i<list.size();i++){
                    UserAndAlbumForm F=new UserAndAlbumForm();
                    BeanUtils.copyProperties(list.get(i),F);
                    userAndAlbumForm.add(i,F);
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return userAndAlbumForm;
    }

    @Override
    public List<UserForm> betterUser() {
        String sqlCheck= "from User order by userAlbumNum desc";
        List<UserForm> userForm=new ArrayList<>();
        try{
            List<UserForm> list= (List<UserForm>) getHibernateTemplate().find(sqlCheck);
            if(list!=null){
                for (int i=0;i<list.size()&&i<5;i++){
                    UserForm F=new UserForm();
                    BeanUtils.copyProperties(list.get(i),F);
                    F.setUserPwd("0");
                    userForm.add(i,F);
                }
            }else{
                return null;
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
        return userForm;
    }
}
