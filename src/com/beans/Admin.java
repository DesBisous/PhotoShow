package com.beans;

import javax.persistence.*;

/**
 * Created by asus on 2016/5/22.
 */
@Entity
@Table(name = "admin")
public class Admin {
    private int id;
    private String phone;
    private String email;
    private String address;
    private String headImg;
    private String adminid;
    private String passwd;
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
    @Column(name = "phone", nullable = false, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "headImg", nullable = false, length = 255)
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Basic
    @Column(name = "adminid", nullable = true, length = 50)
    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    @Basic
    @Column(name = "passwd", nullable = false, length = 32)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;
        if (phone != null ? !phone.equals(admin.phone) : admin.phone != null) return false;
        if (email != null ? !email.equals(admin.email) : admin.email != null) return false;
        if (address != null ? !address.equals(admin.address) : admin.address != null) return false;
        if (headImg != null ? !headImg.equals(admin.headImg) : admin.headImg != null) return false;
        if (adminid != null ? !adminid.equals(admin.adminid) : admin.adminid != null) return false;
        if (passwd != null ? !passwd.equals(admin.passwd) : admin.passwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (adminid != null ? adminid.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", headImg='" + headImg + '\'' +
                ", adminid='" + adminid + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
