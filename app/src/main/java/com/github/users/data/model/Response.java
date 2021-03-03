package com.github.users.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Danilo 15/12/2020.
 */
public class Response implements Serializable {
    public static final String ID = "response";

    @SerializedName("total_count")
    @Expose
    private String name;

    @SerializedName("incomplete_results")
    @Expose
    private String nickname;

    @SerializedName("items")
    @Expose
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}