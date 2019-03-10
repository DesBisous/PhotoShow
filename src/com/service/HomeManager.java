package com.service;

import com.forms.AlbumForm;
import com.forms.MasterinfoForm;

import java.util.List;

public interface HomeManager {
    //计算有此关键词的行数
    public int countAddress(String key);
    //所有相册数据
    public List<AlbumForm> allManAlbum();
    //获取达人信息
    public List<MasterinfoForm> getMasterin();
    public List<AlbumForm> towAlbum();
}
