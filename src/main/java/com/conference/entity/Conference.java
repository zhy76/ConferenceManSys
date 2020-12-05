package com.conference.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    private Integer conferenceId;
    private Integer organizerId;
    private Integer fleetId;
    private Integer hotelId;
    private String conferenceName;
    private Timestamp conferenceStart; //会议开始时间
    private Timestamp conferenceEnd; //会议结束时间
    private String conferenceLocation; //会议地址
    private String conferenceInfo; //会议相关信息


}

