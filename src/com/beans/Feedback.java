package com.beans;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    private int id;
    private Integer fbradio;
    private String fbtext;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fbradio", nullable = true)
    public Integer getFbradio() {
        return fbradio;
    }

    public void setFbradio(Integer fbradio) {
        this.fbradio = fbradio;
    }

    @Basic
    @Column(name = "fbtext", nullable = true, length = 200)
    public String getFbtext() {
        return fbtext;
    }

    public void setFbtext(String fbtext) {
        this.fbtext = fbtext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != feedback.id) return false;
        if (fbradio != null ? !fbradio.equals(feedback.fbradio) : feedback.fbradio != null) return false;
        if (fbtext != null ? !fbtext.equals(feedback.fbtext) : feedback.fbtext != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fbradio != null ? fbradio.hashCode() : 0);
        result = 31 * result + (fbtext != null ? fbtext.hashCode() : 0);
        return result;
    }
}
