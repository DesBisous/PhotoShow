package com.beans;

import javax.persistence.*;

/**
 * Created by Benson on 2016/5/24.
 */
@Entity
@Table(name = "masterinfo")
public class Masterinfo {
    private int id;
    private String nickname;
    private String basicinfo;
    private String introduce;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "basicinfo", nullable = false, length = 255)
    public String getBasicinfo() {
        return basicinfo;
    }

    public void setBasicinfo(String basicinfo) {
        this.basicinfo = basicinfo;
    }

    @Basic
    @Column(name = "introduce", nullable = false, length = 255)
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Masterinfo that = (Masterinfo) o;

        if (id != that.id) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (basicinfo != null ? !basicinfo.equals(that.basicinfo) : that.basicinfo != null) return false;
        if (introduce != null ? !introduce.equals(that.introduce) : that.introduce != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (basicinfo != null ? basicinfo.hashCode() : 0);
        result = 31 * result + (introduce != null ? introduce.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
