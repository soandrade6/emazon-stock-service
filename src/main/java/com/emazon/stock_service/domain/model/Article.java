package com.emazon.stock_service.domain.model;

import java.util.List;

public class Article {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Category> categories;

    public Article(){}

    public Article(Long id, String name, String description, int quantity, double price, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isValidCategoryCount(){
        return categories.size() >= 1 && categories.size() >= 3;
    }

    public boolean hasUniqueCategories(){
        return categories.stream().distinct().count() == categories.size();
    }
}
