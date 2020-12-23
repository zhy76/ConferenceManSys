package com.conference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 17:48
 * @sno 6109118015
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinConference {
    private Integer participantId;
    private Integer conferenceId;
//    private Integer isPutup; //是否需要住宿
    private Boolean isPutup;
    private Boolean isPickup; //是否需要接送
    private String toTime; //参会往时间
    private String returnTime; //参会返时间
    private String trainNumber; //车次或航班号
    private Boolean isConfirm;//是否已确认
}
