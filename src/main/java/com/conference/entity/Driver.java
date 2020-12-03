package com.conference.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private Integer driverId;
    private String driverName;
    private String carNumber; //司机车牌号
    private Integer fleetId; //车队id
    private String driverPass; //司机密码
    private String driverPhone;
    private Boolean isAssign; //是否已派出
}
