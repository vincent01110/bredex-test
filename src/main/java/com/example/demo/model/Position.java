package com.example.demo.model;

public class Position {
    private String title;
    private String address;

    public Position(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
