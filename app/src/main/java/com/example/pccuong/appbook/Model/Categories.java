package com.example.pccuong.appbook.model;

import java.util.List;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class Categories {
    private int id;
    private String name;
    List<Categories> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
