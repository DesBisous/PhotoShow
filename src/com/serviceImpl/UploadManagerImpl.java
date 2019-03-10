package com.serviceImpl;

import com.beans.Album;
import com.dao.UploadDao;
import com.forms.AlbumForm;
import com.service.UploadManager;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * 接收action类传送过来的参数
 *
 */
public class UploadManagerImpl implements UploadManager{
    private UploadDao uploadDao;
    private Album album=new Album();

    public UploadDao getUploadDao() {
        return uploadDao;
    }

    public void setUploadDao(UploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }

    @Override
    public boolean saveHeadImg(int id ,String userHeadImg) throws HibernateException{
        return uploadDao.saveHeadImg(id ,userHeadImg);
    }

    @Override
    public boolean saveGalleryInfo(AlbumForm albumForm) {
        BeanUtils.copyProperties(albumForm,album);
        return uploadDao.saveGalleryInfo(album);
    }

    @Override
    public int haveAlbumNum(int id) {
        return uploadDao.haveAlbumNum(id);
    }

    @Override
    public boolean updateAlbumNum(int id, int num) {
        return uploadDao.updateAlbumNum(id,num);
    }

    @Override
    public ArrayList<AlbumForm> haveAlbumAddr(int id) {
        return uploadDao.haveAlbumAddr(id);
    }

    @Override
    public boolean deleteUnexistsData(int albumId) {
        return uploadDao.deleteUnexistsData(albumId);
    }

    @Override
    public boolean findAlbumByName(int userId, String name) {
        return uploadDao.findAlbumByName(userId,name);
    }

    @Override
    public boolean updateAlbumByName(AlbumForm albumForm) {
        BeanUtils.copyProperties(albumForm,album);
        return uploadDao.updateAlbumByName(album);
    }

    @Override
    public AlbumForm findAlbumByTitle(int userId, String title) {
        return uploadDao.findAlbumByTitle(userId,title);
    }

}
