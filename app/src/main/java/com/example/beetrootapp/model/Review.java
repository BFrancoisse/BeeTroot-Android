package com.example.beetrootapp.model;

public class Review {
    private int id;
    private int note;
    private String commentary;
    private int userId;
    private int farmId;
    private Farm farm;
    private User user;

    public Review(int id, int note, String commentary, int userId, int farmId, Farm farm, User user) {
        this.id = id;
        this.note = note;
        this.commentary = commentary;
        this.userId = userId;
        this.farmId = farmId;
        this.farm = farm;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getCommentary() {
        return commentary;
    }

    public int getUserId() {
        return userId;
    }

    public int getFarmId() {
        return farmId;
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

    public void setNote(int note) {
        this.note = note;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
