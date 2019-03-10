package com.forms;

/**
 * Created by Benson on 2016/5/24.
 */
public class MasterinfoForm {
    private int id;
    private String name;
    private String nickname;
    private String basicinfo;
    private String introduce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBasicinfo() {
        return basicinfo;
    }

    public void setBasicinfo(String basicinfo) {
        this.basicinfo = basicinfo;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "MasterinfoForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", basicinfo='" + basicinfo + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
