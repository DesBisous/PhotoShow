package com.forms;

/**
 * Created by ruihe on 16-5-31.
 */
public class TowAlbumForm {
    private int id;
    private String theme;
    private String title;
    private String albumIntroduction;
    private int good;
    private int userId;
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
