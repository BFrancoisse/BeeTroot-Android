package com.example.beetrootapp.model;

public class Like {
    private int id;
    private Farm farm;
    private User user;

    public Like(int id, Farm farm, User user) {
        this.id = id;
        this.farm = farm;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Farm getFarm() {
        return farm;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
