package com.ulunayev.askar.noteskotlin.models;

public class NoteModel {
    long id;
    int position;
    String title;
    String text;
    String type;
    boolean isSelected;

    public NoteModel(long id, int position, String title, String text, String type, boolean isSelected) {
        this.id = id;
        this.position = position;
        this.title = title;
        this.text = text;
        this.type = type;
        this.isSelected = isSelected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
