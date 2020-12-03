package com.conference.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//住宿表
public class LiveRoom {
    private Integer participantId; //参会者id
    private Integer hotelId; //酒店id
    private String roomId; //房间号
}
