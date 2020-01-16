package com.example.myapplication.data;

import android.app.Application;


public class GlobalVariable extends Application {
    private String name,car;
    private int phone;

    public void setPhone(int phone){
        this.phone = phone;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCar(String car){
        this.car = car;
    }

    public String getName(){
        return name;
    }
    public String getCar(){
        return car;
    }
    public int getPhone(){
        return phone;
    }
}
