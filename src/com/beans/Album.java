package com.beans;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "album")
public class Album {
    private int id;
    private String theme;
    private String title;
    private String albumIntroduction;
    private int good;
    private int userId;
    private Timestamp createTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "theme", nullable = false, length = 20)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 20)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "albumIntroduction", nullable = false, length = 800)
    public String getAlbumIntroduction() {
        return albumIntroduction;
    }

    public void setAlbumIntroduction(String albumIntroduction) {
        this.albumIntroduction = albumIntroduction;
    }

    @Basic
    @Column(name = "good", nullable = false)
    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    @Basic
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "createTime", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (id != album.id) return false;
        if (good != album.good) return false;
        if (userId != album.userId) return false;
        if (theme != null ? !theme.equals(album.theme) : album.theme != null) return false;
        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        if (albumIntroduction != null ? !albumIntroduction.equals(album.albumIntroduction) : album.albumIntroduction != null)
            return false;
        if (createTime != null ? !createTime.equals(album.createTime) : album.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (albumIntroduction != null ? albumIntroduction.hashCode() : 0);
        result = 31 * result + good;
        result = 31 * result + userId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
