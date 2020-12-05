package com.conference.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickUp {
    private Integer participantId; //参加者ID
    private Integer driverId; //司机ID
    private String trainNumber; // 车次航班号
    private Timestamp toTime; //参会往时间
    private Timestamp returnTime; //参会往时间
    private Boolean isFinishPickup; //是否完成接送

}
