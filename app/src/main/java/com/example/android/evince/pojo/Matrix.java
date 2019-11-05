package com.example.android.evince.pojo;

import androidx.room.Entity;

@Entity
public class Matrix {
    private int number;
    private int color;
    private boolean isSelected;

    public Matrix(int number) {
        this.number = number;
    }

    public Matrix(int number, int color) {
        this.number = number;
        this.color = color;
    }

    public Matrix(int number, int color, boolean isSelected) {
        this.number = number;
        this.color = color;
        this.isSelected = isSelected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
