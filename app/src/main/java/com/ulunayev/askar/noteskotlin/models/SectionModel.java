package com.ulunayev.askar.noteskotlin.models;

public class SectionModel {
    String name;
    int positon;

    public SectionModel(int id, String name, int positon) {
        this.name = name;
        this.positon = positon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }
}
