package com.example.beetrootapp.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private Catalog[] catalog;

    public Product(int id, String name, Catalog[] catalog) {
        this.id = id;
        this.name = name;
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Catalog[] getCatalog() {
        return catalog;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCatalog(Catalog[] catalog) {
        this.catalog = catalog;
    }
}
