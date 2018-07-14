package com.aapreneur.appname.resources;

public class MyDataModel {

        private String item;
        private double price;
        private String catagory;
    private boolean type;
    private boolean productStatus;
    private String description;


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

    public void setCategory(String catagory)
        {
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

    public void setDescription(String description) {
        this.description = description;
    }
}
