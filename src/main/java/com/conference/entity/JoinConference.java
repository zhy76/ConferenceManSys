package com.conference.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinConference {
    private Integer participantId;
    private Integer conferenceId;
    private Integer isPutup; //是否需要住宿
    private Boolean isPickup; //是否需要接送
    private Timestamp toTime; //参会往时间
    private Timestamp returnTime; //参会返时间
    private String trainNumber; //车次或航班号
}