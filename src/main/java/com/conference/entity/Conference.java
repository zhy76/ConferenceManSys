package com.conference.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    private Integer conferenceId;
    private Integer organizerId;
    private Integer fleetId;
    private Integer hotelId;
    private String conferenceName;
    private Date conferenceStart; //会议开始时间
    private Date conferenceEnd; //会议结束时间
    private String conferenceLocation; //会议地址
    private String conferenceInfo; //会议相关信息
}

