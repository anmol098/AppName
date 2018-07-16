package com.aapreneur.appname.resources;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyDataModel implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("item")
    private String item;

    @SerializedName("price")
    private double price;

    @SerializedName("catagory")
    private String catagory;

    @SerializedName("type")
    private boolean type;

    @SerializedName("productStatus")
    private boolean productStatus;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("tableNumber")
    private int tableNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item=item;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public String getCategory() {
        return catagory;
    }

    public void setCategory(String catagory) {
        this.catagory=catagory;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getDescription() {
        return description;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
