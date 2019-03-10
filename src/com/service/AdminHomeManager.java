package com.service;

import com.forms.UserAndAlbumForm;
import com.forms.UserForm;

import java.util.List;

/**
 * Created by ruihe on 16-5-22.
 */
public interface AdminHomeManager {
    //count album
    public long numOfAlbum(String keyWords);
    public List<UserAndAlbumForm> maxNumOrderBy();
    public List<UserForm> betterUser();
}
