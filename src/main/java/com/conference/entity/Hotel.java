package com.conference.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private Integer hotelId;
    private String hotelName; //酒店名
    private String hotelLocation; //酒店地址
    private String hotelPhone; //酒店电话
    private String hotelPass; //酒店密码
    private String hotelInfo; //酒店信息
}
