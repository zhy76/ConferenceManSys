package com.conference.entity;


/**
 * @ClassName: Driver
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 16:57
 */
public class Driver {
    private int driverId;
    private String driverDame;
    private String carNumber;
    private String driverPass;
    private String driverPhone;
    private Boolean isAssign;

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", driverDame='" + driverDame + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", driverPass='" + driverPass + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", isAssign=" + isAssign +
                '}';
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverDame() {
        return driverDame;
    }

    public void setDriverDame(String driverDame) {
        this.driverDame = driverDame;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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
