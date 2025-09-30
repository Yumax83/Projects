package com.example.chatapp.utils.model;

import com.google.firebase.Timestamp;

public class UserModel {

    private String phone, username; //хранит номер телефона и имя пользователя
    private Timestamp createdTimestamp; //хранит время создания пользователя

    public UserModel() { //
    }

    public UserModel(String phone, String username, Timestamp createdTimestamp) { //
        this.phone = phone;
        this.username = username;
        this.createdTimestamp = createdTimestamp;
    }

    public String getPhone() {
        return phone;
    }//

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
