package com.conference.entity;

public class Hotel {
    private int hotel_id;
    private String hotel_name ;
    private String hotel_location;
    private String hotel_phone;
    private String hotel_pass;
    private String hotel_info;

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_location='" + hotel_location + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_pass='" + hotel_pass + '\'' +
                ", hotel_info='" + hotel_info + '\'' +
                '}';
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_pass() {
        return hotel_pass;
    }

    public void setHotel_pass(String hotel_pass) {
        this.hotel_pass = hotel_pass;
    }

    public String getHotel_info() {
        return hotel_info;
    }

    public void setHotel_info(String hotel_info) {
        this.hotel_info = hotel_info;
    }
}
