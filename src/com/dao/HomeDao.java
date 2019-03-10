package com.dao;

import com.forms.AlbumForm;
import com.forms.MasterinfoForm;

import java.util.List;


public interface HomeDao {
    public int countAddress(String key);
    public List<AlbumForm> allManAlbum();
    public List<AlbumForm> towAlbum();
    //获取达人信息
    public List<MasterinfoForm> getMasterin();
}
