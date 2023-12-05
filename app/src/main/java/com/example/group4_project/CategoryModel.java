package com.example.group4_project;

public class CategoryModel {
    private String categoryName;
    private int categoryImage;

    public CategoryModel(String categoryName, int categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryImage() {
        return categoryImage;
    }
}
