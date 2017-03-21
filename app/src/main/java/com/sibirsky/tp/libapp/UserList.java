package com.sibirsky.tp.libapp;

import java.util.List;

/**
 * Created by sibirsky on 21.03.17.
 */

public class UserList {
    private Integer id;
    private String name;
    private Integer size;
    private List<UserModel> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
