package com.aapreneur.appname.resources;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static List<Item> items = new ArrayList<Item>();

    public static void insert(Item item) {
        items.add(item);
    }

    public static List<Item> contents() {
        return items;
    }

    public static void update(MyDataModel product) {
        int index = getIndex(product);
        int quantity = items.get(index).getQuantity() + 1;
        items.get(index).setQuantity(quantity);

    }

    public static void remove(MyDataModel product) {
        int index = getIndex(product);
        int quantity = items.get(index).getQuantity() - 1;
        items.get(index).setQuantity(quantity);
        if (items.get(index).getQuantity() == 0) {
            items.remove(index);
        }
    }

    public static double total() {
        double s = 0;
        for (Item item : items) {
            s += item.getProduct().getPrice() * item.getQuantity();
        }
        return s;
    }

    public static int size() {
        int size = 0;
        for (Item item : items) {
            size = size + 1;
        }
        return size;
    }

    public static boolean isExists(MyDataModel product) {
        return getIndex(product) != -1;
    }

    private static int getIndex(MyDataModel product) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == product.getId()) {
                return i;
            }
        }
        return -1;
    }
}