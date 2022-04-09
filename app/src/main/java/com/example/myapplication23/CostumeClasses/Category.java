package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class Category extends Support_Class implements Serializable
{
    private int CategoryID;
    private String CategoryName;
    private String CategoryImage;

    public Category() {
    }

    public Category(int categoryID, String categoryName, String categoryImage) {
        CategoryID = categoryID;
        CategoryName = categoryName;
        CategoryImage = categoryImage;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    @Override
    public String getCategoryName() {
        return CategoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "CategoryID=" + CategoryID +
                ", CategoryName='" + CategoryName + '\'' +
                ", CategoryImage='" + CategoryImage + '\'' +
                '}';
    }
}
