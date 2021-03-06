package com.anti.drama.mybookkeeper.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    private Long id;

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    public UserModel(Long id, String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public UserModel(String username,String password){
        this.userName = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
