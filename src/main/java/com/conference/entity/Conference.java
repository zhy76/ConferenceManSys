package com.conference.entity;


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


}