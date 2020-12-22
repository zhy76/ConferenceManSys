package com.conference.util;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/22 0:10
 **/

//为了方便会议的展示和修改新建一个工具类
public class ConferenceUtils {
    private Integer conferenceId;

    private Integer organizerId;
    private String organizerUnit; //组织者id对应的组织单位

    private Integer fleetId;
    private String fleetName; //车队id对应的车队名

    private Integer hotelId;
    private String hotelName; //酒店id对应的酒店名

    private String conferenceName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String conferenceStart; //会议开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String conferenceEnd; //会议结束时间
    private String conferenceLocation; //会议地址
    private String conferenceInfo; //会议相关信息

    public ConferenceUtils() {
    }

    public ConferenceUtils(Integer conferenceId, Integer organizerId, Integer fleetId, Integer hotelId, String conferenceName, String conferenceStart, String conferenceEnd, String conferenceLocation, String conferenceInfo) {
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


    public ConferenceUtils(Integer conferenceId, Integer organizerId, String organizerUnit, Integer fleetId, String fleetName, Integer hotelId, String hotelName, String conferenceName, String conferenceStart, String conferenceEnd, String conferenceLocation, String conferenceInfo) {
        this.conferenceId = conferenceId;
        this.organizerId = organizerId;
        this.organizerUnit = organizerUnit;
        this.fleetId = fleetId;
        this.fleetName = fleetName;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.conferenceName = conferenceName;
        this.conferenceStart = conferenceStart;
        this.conferenceEnd = conferenceEnd;
        this.conferenceLocation = conferenceLocation;
        this.conferenceInfo = conferenceInfo;
    }


    @Override
    public String toString() {
        return "ConferenceUtils{" +
                "conferenceId=" + conferenceId +
                ", organizerId=" + organizerId +
                ", organizerUnit='" + organizerUnit + '\'' +
                ", fleetId=" + fleetId +
                ", fleetName='" + fleetName + '\'' +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                ", conferenceStart='" + conferenceStart + '\'' +
                ", conferenceEnd='" + conferenceEnd + '\'' +
                ", conferenceLocation='" + conferenceLocation + '\'' +
                ", conferenceInfo='" + conferenceInfo + '\'' +
                '}';
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public void setOrganizerUnit(String organizerUnit) {
        this.organizerUnit = organizerUnit;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void setConferenceStart(String conferenceStart) {
        this.conferenceStart = conferenceStart;
    }

    public void setConferenceEnd(String conferenceEnd) {
        this.conferenceEnd = conferenceEnd;
    }

    public void setConferenceLocation(String conferenceLocation) {
        this.conferenceLocation = conferenceLocation;
    }

    public void setConferenceInfo(String conferenceInfo) {
        this.conferenceInfo = conferenceInfo;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public String getOrganizerUnit() {
        return organizerUnit;
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public String getConferenceStart() {
        return conferenceStart;
    }

    public String getConferenceEnd() {
        return conferenceEnd;
    }

    public String getConferenceLocation() {
        return conferenceLocation;
    }

    public String getConferenceInfo() {
        return conferenceInfo;
    }
}
