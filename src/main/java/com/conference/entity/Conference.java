package com.conference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 20:40
 * @sno 6109118015
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
