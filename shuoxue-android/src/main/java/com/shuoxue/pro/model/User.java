package com.shuoxue.pro.model;

import java.io.Serializable;

/**
 * 用户实体
 * Created by orang on 2016/12/23.
 */

public class User implements Serializable {
    private String userId;
    private String account;
    private String pwd;
    private int sex;
    private String title;
    private String groupId;
    private int status;
    private String create_time;
    private String modify_time;
    private int utype;
    private String phone;
    private String email;
    private String thumb;
    private String address;
    private String store_name;


    //加盟店经营品类
    private String napOperateCat;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex +
                ", title='" + title + '\'' +
                ", groupId='" + groupId + '\'' +
                ", status=" + status +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", utype=" + utype +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", thumb='" + thumb + '\'' +
                ", address='" + address + '\'' +
                ", store_name='" + store_name + '\'' +
                '}';
    }
}
