package com.conference.entity;

public class LiveRoom {
    private int participantId ;
    private int hotelId;
    private int conferenceId;
    private String roomId;

    @Override
    public String toString() {
        return "LiveRoom{" +
                "participantId=" + participantId +
                ", hotelId=" + hotelId +
                ", conferenceId=" + conferenceId +
                ", roomId='" + roomId + '\'' +
                '}';
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
