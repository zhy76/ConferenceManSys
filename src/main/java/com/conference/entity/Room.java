package com.conference.entity;

public class Room {
    private String roomId;
    private int hotelId;
    private int isLive;
    private String roomType;

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", hotelId=" + hotelId +
                ", isLive=" + isLive +
                ", roomType='" + roomType + '\'' +
                '}';
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getIsLive() {
        return isLive;
    }

    public void setIsLive(int isLive) {
        this.isLive = isLive;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
