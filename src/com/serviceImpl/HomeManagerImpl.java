package com.serviceImpl;

import com.dao.HomeDao;
import com.forms.AlbumForm;
import com.forms.MasterinfoForm;
import com.service.HomeManager;

import java.util.List;


public class HomeManagerImpl implements HomeManager{
    private HomeDao homeDao;

    public HomeDao getHomeDao() {
        return homeDao;
    }

    public void setHomeDao(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    @Override
    public int countAddress(String key) {
        return homeDao.countAddress(key);
    }

    @Override
    public List<AlbumForm> allManAlbum() {
        return homeDao.allManAlbum();
    }

    @Override
    public List<MasterinfoForm> getMasterin() {
        return homeDao.getMasterin();
    }

    @Override
    public List<AlbumForm> towAlbum() {
        return homeDao.towAlbum();
    }
}
