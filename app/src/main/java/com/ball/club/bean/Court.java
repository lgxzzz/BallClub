package com.ball.club.bean;

import java.io.Serializable;

public class Court implements Serializable {
    //球场名称
    String name;
    //球场位置
    String position;
    //球场人数
    String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
