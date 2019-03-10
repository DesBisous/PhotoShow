package com.beans;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {
    private int id;
    private String userName;
    private String userPwd;
    private String userPhNum;
    private String userHeadImg;
    private String userEmail;
    private String userAddress;
    private String userId;
    private int userAlbumNum;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userPwd", nullable = false, length = 20)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "userPhNum", nullable = true, length = 12)
    public String getUserPhNum() {
        return userPhNum;
    }

    public void setUserPhNum(String userPhNum) {
        this.userPhNum = userPhNum;
    }

    @Basic
    @Column(name = "userHeadImg", nullable = true, length = 255)
    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    @Basic
    @Column(name = "userEmail", nullable = true, length = 45)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "userAddress", nullable = true, length = 50)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "userId", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Basic
    @Column(name = "userAlbumNum", nullable = false)
    public int getUserAlbumNum() {
        return userAlbumNum;
    }

    public void setUserAlbumNum(int userAlbumNum) {
        this.userAlbumNum = userAlbumNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userPwd != null ? !userPwd.equals(user.userPwd) : user.userPwd != null) return false;
        if (userPhNum != null ? !userPhNum.equals(user.userPhNum) : user.userPhNum != null) return false;
        if (userHeadImg != null ? !userHeadImg.equals(user.userHeadImg) : user.userHeadImg != null) return false;
        if (userEmail != null ? !userEmail.equals(user.userEmail) : user.userEmail != null) return false;
        if (userAddress != null ? !userAddress.equals(user.userAddress) : user.userAddress != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPwd != null ? userPwd.hashCode() : 0);
        result = 31 * result + (userPhNum != null ? userPhNum.hashCode() : 0);
        result = 31 * result + (userHeadImg != null ? userHeadImg.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

}
