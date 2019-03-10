package com.beans;

import javax.persistence.*;

/**
 * Created by Benson on 2016/5/29.
 */
@Entity
@Table(name = "persontheme")
public class Persontheme {
    private int id;
    private String userid;
    private String theme;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = false, length = 255)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "theme", nullable = false, length = 255)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persontheme that = (Persontheme) o;

        if (id != that.id) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }
}
