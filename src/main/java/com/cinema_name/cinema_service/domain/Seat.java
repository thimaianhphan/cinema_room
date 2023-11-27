package com.cinema_name.cinema_service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

    private int row;

    private int column;

    private int price;
    public Seat() {}
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        updatePrice();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public void setRow(int row) {
        this.row = row;
        updatePrice();
    }

    public void setColumn(int column) {
        this.column = column;
    }

    private void updatePrice() {
        this.price = row <= 4 ? 10 : 8;
    }

    public boolean equals(Object obj) {
        if (obj instanceof  Seat seat)
            return this.row == seat.row && this.column == seat.column;
        return false;
    }
}
