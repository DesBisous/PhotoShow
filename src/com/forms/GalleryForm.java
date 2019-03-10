package com.forms;


public class GalleryForm {
    private int userId;
    private String title;
    private String imgePath;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgePath() {
        return imgePath;
    }

    public void setImgePath(String imgePath) {
        this.imgePath = imgePath;
    }

    @Override
    public String toString() {
        return "GalleryForm{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", ImgePath='" + imgePath + '\'' +
                '}';
    }
}
