package com.example.store3.data;

public class ItemForForm {
    private String name;
    private float price;
    private ItemCategory categoryName;
    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ItemCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(ItemCategory categoryName) {
        this.categoryName = categoryName;
    }
}
