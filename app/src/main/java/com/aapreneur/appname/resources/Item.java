package com.aapreneur.appname.resources;

import java.io.Serializable;

public class Item implements Serializable {

    private MyDataModel product;

    private int quantity;

    public Item() {
    }

    public Item(MyDataModel product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public MyDataModel getProduct() {
        return this.product;
    }

    public void setProduct(MyDataModel product) {
        this.product = product;
    }

    public int getQuantity() {

        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
