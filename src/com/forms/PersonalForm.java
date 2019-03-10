package com.forms;


import java.sql.Timestamp;

public class PersonalForm {
    private int id;
    private String theme;
    private String title;
    private String albumIntroduction;
    private int good;
    private int userId;
    private Timestamp createTime;
    private String Imge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getImge() {
        return Imge;
    }

    public void setImge(String imge) {
        Imge = imge;
    }
}
