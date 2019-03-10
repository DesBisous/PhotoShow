package com.forms;

/**
 * Created by Benson on 2016/5/25.
 */
public class FeedbackForm {
    private int id;
    private Integer fbradio;
    private String fbtext;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFbradio() {
        return fbradio;
    }

    public void setFbradio(Integer fbradio) {
        this.fbradio = fbradio;
    }

    public String getFbtext() {
        return fbtext;
    }

    public void setFbtext(String fbtext) {
        this.fbtext = fbtext;
    }

    @Override
    public String toString() {
        return "FeedbackForm{" +
                "id=" + id +
                ", fbradio=" + fbradio +
                ", fbtext='" + fbtext + '\'' +
                '}';
    }
}
