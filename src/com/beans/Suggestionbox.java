package com.beans;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "suggestionbox")
public class Suggestionbox {
    private int id;
    private int userid;
    private int suggestionType;
    private String suggestionTitle;
    private String sugContent;
    private Timestamp sugTime;
    private String csId;
    private String csContent;
    private Timestamp csTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "suggestionType", nullable = false)
    public int getSuggestionType() {
        return suggestionType;
    }

    public void setSuggestionType(int suggestionType) {
        this.suggestionType = suggestionType;
    }

    @Basic
    @Column(name = "suggestionTitle", nullable = false, length = 60)
    public String getSuggestionTitle() {
        return suggestionTitle;
    }

    public void setSuggestionTitle(String suggestionTitle) {
        this.suggestionTitle = suggestionTitle;
    }

    @Basic
    @Column(name = "sugContent", nullable = false, length = 2000)
    public String getSugContent() {
        return sugContent;
    }

    public void setSugContent(String sugContent) {
        this.sugContent = sugContent;
    }

    @Basic
    @Column(name = "sugTime", nullable = false)
    public Timestamp getSugTime() {
        return sugTime;
    }

    public void setSugTime(Timestamp sugTime) {
        this.sugTime = sugTime;
    }

    @Basic
    @Column(name = "csId", nullable = true, length = 32)
    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    @Basic
    @Column(name = "csContent", nullable = true, length = 2000)
    public String getCsContent() {
        return csContent;
    }

    public void setCsContent(String csContent) {
        this.csContent = csContent;
    }

    @Basic
    @Column(name = "csTime", nullable = true)
    public Timestamp getCsTime() {
        return csTime;
    }

    public void setCsTime(Timestamp csTime) {
        this.csTime = csTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suggestionbox that = (Suggestionbox) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (suggestionType != that.suggestionType) return false;
        if (suggestionTitle != null ? !suggestionTitle.equals(that.suggestionTitle) : that.suggestionTitle != null)
            return false;
        if (sugContent != null ? !sugContent.equals(that.sugContent) : that.sugContent != null) return false;
        if (sugTime != null ? !sugTime.equals(that.sugTime) : that.sugTime != null) return false;
        if (csId != null ? !csId.equals(that.csId) : that.csId != null) return false;
        if (csContent != null ? !csContent.equals(that.csContent) : that.csContent != null) return false;
        if (csTime != null ? !csTime.equals(that.csTime) : that.csTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userid;
        result = 31 * result + suggestionType;
        result = 31 * result + (suggestionTitle != null ? suggestionTitle.hashCode() : 0);
        result = 31 * result + (sugContent != null ? sugContent.hashCode() : 0);
        result = 31 * result + (sugTime != null ? sugTime.hashCode() : 0);
        result = 31 * result + (csId != null ? csId.hashCode() : 0);
        result = 31 * result + (csContent != null ? csContent.hashCode() : 0);
        result = 31 * result + (csTime != null ? csTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Suggestionbox{" +
                "id=" + id +
                ", userid=" + userid +
                ", suggestionType=" + suggestionType +
                ", suggestionTitle='" + suggestionTitle + '\'' +
                ", sugContent='" + sugContent + '\'' +
                ", sugTime=" + sugTime +
                ", csId='" + csId + '\'' +
                ", csContent='" + csContent + '\'' +
                ", csTime=" + csTime +
                '}';
    }
}
