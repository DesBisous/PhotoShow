package com.forms;

import java.sql.Timestamp;


public class SearchForm {
    private String theme;
    private String title;
    private String albumIntroduction;
    private int good;
    private Timestamp createTime;
    private String Owner;
    private String Imge;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getImge() {
        return Imge;
    }

    public void setImge(String imge) {
        Imge = imge;
    }

    @Override
    public String toString() {
        return "AlbumForm{" +
                "theme='" + theme + '\'' +
                ", title='" + title + '\'' +
                ", albumIntroduction='" + albumIntroduction + '\'' +
                ", good=" + good +
                ", createTime=" + createTime +
                ", Owner='" + Owner + '\'' +
                ", Imge='" + Imge + '\'' +
                '}';
    }
}
