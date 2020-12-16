package com.conference.entity;

/**
 * @Description 会议类
 * @Author 左海余
 * @Date 2020/12/10 21:46
 * @sno 6109118041
 */
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
public class Conference {
    private Integer conferenceId;
    private Integer organizerId;
    private Integer fleetId;
    private Integer hotelId;
    private String conferenceName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp conferenceStart; //会议开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp conferenceEnd; //会议结束时间
    private String conferenceLocation; //会议地址
    private String conferenceInfo; //会议相关信息

    public Conference(Integer conferenceId, Integer organizerId, Integer fleetId, Integer hotelId, String conferenceName, Timestamp conferenceStart, Timestamp conferenceEnd, String conferenceLocation, String conferenceInfo) {
        this.conferenceId = conferenceId;
        this.organizerId = organizerId;
        this.fleetId = fleetId;
        this.hotelId = hotelId;
        this.conferenceName = conferenceName;
        this.conferenceStart = conferenceStart;
        this.conferenceEnd = conferenceEnd;
        this.conferenceLocation = conferenceLocation;
        this.conferenceInfo = conferenceInfo;
    }

    public Conference() {
    }

    @Override
    public String toString() {
        return "Conference{" +
                "conferenceId=" + conferenceId +
                ", organizerId=" + organizerId +
                ", fleetId=" + fleetId +
                ", hotelId=" + hotelId +
                ", conferenceName='" + conferenceName + '\'' +
                ", conferenceStart=" + conferenceStart +
                ", conferenceEnd=" + conferenceEnd +
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

    public Timestamp getConferenceStart() {
        return conferenceStart;
    }

    public void setConferenceStart(Timestamp conferenceStart) {
        this.conferenceStart = conferenceStart;
    }

    public Timestamp getConferenceEnd() {
        return conferenceEnd;
    }

    public void setConferenceEnd(Timestamp conferenceEnd) {
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