package com.daoImpl;

import com.beans.Album;
import com.beans.User;
import com.dao.UploadDao;
import com.forms.AlbumForm;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.ArrayList;


public class UploadDaoImpl  extends HibernateDaoSupport implements UploadDao {

    @Override
    public boolean saveHeadImg(int id ,String userHeadImg) {
        String sqlUpdate="update User set userHeadImg=? where id=?";
        try{
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().bulkUpdate(sqlUpdate,userHeadImg,id);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveGalleryInfo(Album album) {
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().save(album);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int haveAlbumNum(int id) {
        User user=new User();
        int num;
        String sqlCheck="from User where id=?";
        try{
            ArrayList<User> list= (ArrayList) getHibernateTemplate().find(sqlCheck,id);
            BeanUtils.copyProperties(list.get(0),user);
            num=user.getUserAlbumNum();
        }catch (HibernateException e){
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    @Override
    public boolean updateAlbumNum(int id, int num) {
        String sqlUpdate="update User set userAlbumNum=? where id=?";
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().bulkUpdate(sqlUpdate,num,id);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<AlbumForm> haveAlbumAddr(int id) {
        ArrayList<AlbumForm> albumForm=new ArrayList<AlbumForm>();
        String sqlCheck="from Album where userId=?";
        try {
            ArrayList<Album> album= (ArrayList<Album>) getHibernateTemplate().find(sqlCheck,id);
            for(int i=0; i<album.size(); i++){
                //这个创建对象一定要循环重新创建，否则只能取到最后一个的值
                AlbumForm form=new AlbumForm();
                BeanUtils.copyProperties(album.get(i),form);
                albumForm.add(i,form);
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return albumForm;
        }
        return albumForm;
    }

    @Override
    public boolean deleteUnexistsData(int albumId) {
        Album album=new Album();
        album.setId(albumId);
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            album= (Album) getHibernateTemplate().getSessionFactory().getCurrentSession().load(Album.class,albumId);
            getHibernateTemplate().delete(album);
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean findAlbumByName(int userId, String name) {
        String sqlCheck="from Album where userId=? and title=?";
        try {
            ArrayList<Album> album= (ArrayList<Album>) getHibernateTemplate().find(sqlCheck,userId,name);
            if(album.size()!=0){
                return true;
            }
        }catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean updateAlbumByName(Album album) {
        String sqlUpdate="update Album set albumIntroduction=?,theme=? where userId=? and title=?";
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            getHibernateTemplate().bulkUpdate(sqlUpdate,album.getAlbumIntroduction(),album.getTheme(),album.getUserId(),album.getTitle());
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public AlbumForm findAlbumByTitle(int userId, String title) {
        AlbumForm albumForm=new AlbumForm();
        String sqlCheck="from Album where userId=? and title=?";
        try {
            ArrayList<Album> album= (ArrayList<Album>) getHibernateTemplate().find(sqlCheck,userId,title);
            BeanUtils.copyProperties(album.get(0),albumForm);
        }catch (HibernateException e) {
            e.printStackTrace();
            return albumForm;
        }
        return albumForm;
    }
}
