package com.conference.entity;

public class LiveRoom {
    private int participant_id ;
    private int hotel_id;
    private int conference_id;
    private String room_id;

    @Override
    public String toString() {
        return "liveRoom{" +
                "participant_id=" + participant_id +
                ", hotel_id=" + hotel_id +
                ", conference_id=" + conference_id +
                ", room_id='" + room_id + '\'' +
                '}';
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getConference_id() {
        return conference_id;
    }

    public void setConference_id(int conference_id) {
        this.conference_id = conference_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
