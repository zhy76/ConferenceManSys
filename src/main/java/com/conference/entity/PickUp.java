package com.conference.entity;

import java.util.Date;

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
    private Date toTime; //参会往时间
    private Date returnTime; //参会往时间
    private Boolean isFinishPickUp; //是否完成接送

}
