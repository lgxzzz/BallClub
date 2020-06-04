package com.ball.club.bean;

import java.io.Serializable;

/**
 * Created
 */
public class Store implements Serializable {
    private  int id;
    private String name,type,money,price,bianhao,index;
    private int picture;
    public Store() {
    }

    public Store(int id, String name, String type, String money, String price, int picture, String bianhao) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.money = money;
        this.price = price;
        this.picture = picture;
        this.bianhao = bianhao;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public int getpicture() {
        return picture;
    }

    public void setpicture(int picture) {
        this.picture = picture;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
