package com.conference.entity;

public class Hotel {
    private int hotelId;
    private String hotelName ;
    private String hotelLocation;
    private String hotelPhone;
    private String hotelPass;
    private String hotelInfo;

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelLocation='" + hotelLocation + '\'' +
                ", hotelPhone='" + hotelPhone + '\'' +
                ", hotelPass='" + hotelPass + '\'' +
                ", hotelInfo='" + hotelInfo + '\'' +
                '}';
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelPass() {
        return hotelPass;
    }

    public void setHotelPass(String hotelPass) {
        this.hotelPass = hotelPass;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }
}
