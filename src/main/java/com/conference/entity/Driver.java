package com.conference.entity;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: Driver
 * @Description: todo https://www.jianshu.com/p/c8686fa5ef63
 * @Author: Lance
 * @Date: 2020/12/1 16:57
 */


public class Driver {

    private Integer driverId;
    @NotNull(message = "姓名必须存在")
    @Length(min = 1, max = 10, message = "姓名长度应该在1至10之间")
    @Pattern(regexp = "^[^\\s]+$", message = "用户名不能包含空白字符")
    private String driverName;

//    @NotNull(message = "车牌号必须存在")
    private String carNumber;

    private Integer fleetId;

    @NotNull(message = "密码必须存在")
    @Length(min = 6, max = 26, message = "密码长度应该在6至26之间")
    @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符")
    private String driverPass;

    @NotNull(message = "电话号码必须存在")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式")
    private String driverPhone;

    private Boolean isAssign;

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", fleetId=" + fleetId +
                ", driverPass='" + driverPass + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", isAssign=" + isAssign +
                '}';
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Driver(Integer driverId, @NotNull(message = "姓名必须存在") @Length(min = 1, max = 10, message = "姓名长度应该在1至10之间") @Pattern(regexp = "^[^\\s]+$", message = "用户名不能包含空白字符") String driverName, String carNumber, Integer fleetId, @NotNull(message = "密码必须存在") @Length(min = 6, max = 26, message = "密码长度应该在6至26之间") @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符") String driverPass, @NotNull(message = "电话号码必须存在") @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式") String driverPhone, Boolean isAssign) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.carNumber = carNumber;
        this.fleetId = fleetId;
        this.driverPass = driverPass;
        this.driverPhone = driverPhone;
        this.isAssign = isAssign;
    }

    public Driver() {
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public String getDriverPass() {
        return driverPass;
    }

    public void setDriverPass(String driverPass) {
        this.driverPass = driverPass;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Boolean getAssign() {
        return isAssign;
    }

    public void setAssign(Boolean assign) {
        isAssign = assign;
    }
}
