package com.forms;

import java.sql.Timestamp;


public class SuggestionForm {
    private Integer id = null;   //意见箱id
    private Integer userId = null;   //意见箱投递者
    private Integer sugradio = null;    //意见类型
    private String sugtitle = null; //意见标题
    private String sugtext = null;  //意见内容
    private Timestamp sugTime = null;    //意见投递时间
    private String csId = null;     //客服回复者
    private String cstext = null;   //客服回复内容
    private Timestamp csTime = null;     //客服回复时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSugradio() {
        return sugradio;
    }

    public void setSugradio(Integer sugradio) {
        this.sugradio = sugradio;
    }

    public String getSugtitle() {
        return sugtitle;
    }

    public void setSugtitle(String sugtitle) {
        this.sugtitle = sugtitle;
    }

    public String getSugtext() {
        return sugtext;
    }

    public void setSugtext(String sugtext) {
        this.sugtext = sugtext;
    }

    public Timestamp getSugTime() {
        return sugTime;
    }

    public void setSugTime(Timestamp sugTime) {
        this.sugTime = sugTime;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getCstext() {
        return cstext;
    }

    public void setCstext(String cstext) {
        this.cstext = cstext;
    }

    public Timestamp getCsTime() {
        return csTime;
    }

    public void setCsTime(Timestamp csTime) {
        this.csTime = csTime;
    }

    @Override
    public String toString() {
        return "SuggestionForm{" +
                "userId='" + userId + '\'' +
                ", sugradio=" + sugradio +
                ", sugtitle='" + sugtitle + '\'' +
                ", sugtext='" + sugtext + '\'' +
                ", sugTime=" + sugTime +
                ", csId='" + csId + '\'' +
                ", cstext='" + cstext + '\'' +
                ", csTime=" + csTime +
                '}';
    }
}
