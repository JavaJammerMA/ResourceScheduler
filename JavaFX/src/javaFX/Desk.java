/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFX;

/**
 *
 * @author ejenkins
 */
public class Desk {

    private final int row, column;
    private final String name;
    private boolean booked;
    private final String features;
    private String bookee;

    public Desk(int row, int column, String name, String features) {
        this.row = row;
        this.column = column;
        this.name = name;
        this.features = features;
        booked = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getName() {
        return name;
    }

    public String getFeatures() {
        return features;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getBookee() {
        return bookee;
    }

    public void setBookee(String bookee) {
        this.bookee = bookee;
    }
}
