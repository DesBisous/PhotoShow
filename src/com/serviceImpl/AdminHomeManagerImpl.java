package com.serviceImpl;

import com.dao.AdminHomeDao;
import com.forms.UserAndAlbumForm;
import com.forms.UserForm;
import com.service.AdminHomeManager;

import java.util.List;

/**
 * Created by ruihe on 16-5-22.
 */
public class AdminHomeManagerImpl implements AdminHomeManager {
    private AdminHomeDao adminHomeDao;

    public AdminHomeDao getAdminHomeDao() {
        return adminHomeDao;
    }

    public void setAdminHomeDao(AdminHomeDao adminHomeDao) {
        this.adminHomeDao = adminHomeDao;
    }

    @Override
    public long numOfAlbum(String keyWords) {
        return adminHomeDao.numOfAlbum(keyWords);
    }

    @Override
    public List<UserAndAlbumForm> maxNumOrderBy() {
        return adminHomeDao.maxNumOrderBy();
    }

    @Override
    public List<UserForm> betterUser() {
        return adminHomeDao.betterUser();
    }
}
