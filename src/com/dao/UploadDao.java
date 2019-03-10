package com.dao;

import com.beans.Album;
import com.forms.AlbumForm;

import java.util.ArrayList;


public interface UploadDao {
    public boolean saveHeadImg(int id, String userHeadImg);
    public boolean saveGalleryInfo(Album album);
    public int haveAlbumNum(int id);
    public boolean updateAlbumNum(int id, int num);
    public ArrayList<AlbumForm> haveAlbumAddr(int id);
    public boolean deleteUnexistsData(int albumId);
    public boolean findAlbumByName(int userId, String name);
    public boolean updateAlbumByName(Album album);
    public AlbumForm findAlbumByTitle(int userId, String title);
}
