package com.forms;

/**
 * Created by ruihe on 16-5-26.
 */
public class UserAndAlbumForm {
    private int id=0;
    private int good=0;
    private int userAlbumNum=0;
    private String userHeadImg=null;
    private String userId=null;
    private String userName=null;
    private String theme=null;
    private String title=null;
    private String albumIntroduction=null;

    public UserAndAlbumForm(){
        super();
    }

    public UserAndAlbumForm(int id,int good,int userAlbumNum,String userHeadImg,String userId,String userName,String theme,String title,String albumIntroduction){
        super();
        this.id = id;
        this.good = good;
        this.userAlbumNum=userAlbumNum;
        this.userHeadImg = userHeadImg;
        this.userId = userId;
        this.userName = userName;
        this.theme = theme;
        this.title = title;
        this.albumIntroduction = albumIntroduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumIntroduction() {
        return albumIntroduction;
    }

    public void setAlbumIntroduction(String albumIntroduction) {
        this.albumIntroduction = albumIntroduction;
    }
}
