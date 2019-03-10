package com.service;

import com.forms.AlbumForm;

import java.util.ArrayList;


public interface UploadManager {
    //保存头像地址
    public boolean saveHeadImg(int id, String userHeadImg);
    //保存相册基本信息
    public boolean saveGalleryInfo(AlbumForm albumForm);
    //获取相册数量
    public int haveAlbumNum(int id);
    //更新相册数量
    public boolean updateAlbumNum(int id, int num);
    //获取个人相册集合
    public ArrayList<AlbumForm> haveAlbumAddr(int id);
    //删除不存在的相册数据
    public boolean deleteUnexistsData(int albumId);
    //通过相册名称找相应相册
    public boolean findAlbumByName(int userId, String name);
    //通过相册名更新表
    public boolean updateAlbumByName(AlbumForm albumForm);
    //通过title和id取得一个相册
    public AlbumForm findAlbumByTitle(int userId, String title);
}
