package com.ball.club.bean;

import java.io.Serializable;

/**
 * Created
 */
public class StoreDingDan implements Serializable {
    private  int id;
    private String user, store_id,name,type,money,price,bianhao,time;
    private int picture;
    public StoreDingDan() {
    }


    public StoreDingDan(int id, String store_id, String user, String name, String type, String money, String price, int picture, String bianhao, String time) {
        this.id = id;
        this.store_id = store_id;
        this.user = user;
        this.name = name;
        this.type = type;
        this.money = money;
        this.price = price;
        this.picture = picture;
        this.bianhao = bianhao;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
