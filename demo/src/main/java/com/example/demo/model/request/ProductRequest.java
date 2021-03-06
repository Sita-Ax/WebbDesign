package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {

    //this is what coming by json in our controller(the input=post)
    private String name, category;
    private int cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
