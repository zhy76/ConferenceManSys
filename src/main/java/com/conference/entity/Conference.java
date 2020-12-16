package com.conference.entity;


/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 20:40
 * @sno 6109118015
 */
public class Conference {
    private Integer conferenceId;
    private Integer organizerId;
    private Integer fleetId;
    private Integer hotelId;
    private String conferenceName;
    private String conferenceStart; //会议开始时间
    private String conferenceEnd; //会议结束时间
    private String conferenceLocation; //会议地址
    private String conferenceInfo; //会议相关信息

    @Override
    public String toString() {
        return "Conference{" +
                "conferenceId=" + conferenceId +
                ", organizerId=" + organizerId +
                ", fleetId=" + fleetId +
                ", hotelId=" + hotelId +
                ", conferenceName='" + conferenceName + '\'' +
                ", conferenceStart='" + conferenceStart + '\'' +
                ", conferenceEnd='" + conferenceEnd + '\'' +
                ", conferenceLocation='" + conferenceLocation + '\'' +
                ", conferenceInfo='" + conferenceInfo + '\'' +
                '}';
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceStart() {
        return conferenceStart;
    }

    public void setConferenceStart(String conferenceStart) {
        this.conferenceStart = conferenceStart;
    }

    public String getConferenceEnd() {
        return conferenceEnd;
    }

    public void setConferenceEnd(String conferenceEnd) {
        this.conferenceEnd = conferenceEnd;
    }

    public String getConferenceLocation() {
        return conferenceLocation;
    }

    public void setConferenceLocation(String conferenceLocation) {
        this.conferenceLocation = conferenceLocation;
    }

    public String getConferenceInfo() {
        return conferenceInfo;
    }

    public void setConferenceInfo(String conferenceInfo) {
        this.conferenceInfo = conferenceInfo;
    }
}
