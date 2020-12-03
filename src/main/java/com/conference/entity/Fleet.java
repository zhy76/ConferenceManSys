package com.conference.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fleet {
    private Integer fleetId;  //车队id
    private String fleetName; //车队名
    private String fleetPass; //车队密码
    private String fleetPhone; //车队电话号码
}
