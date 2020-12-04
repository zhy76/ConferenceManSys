package com.conference.entity;



/**
 * @ClassName: Driver
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 16:57
 */


public class Driver {
    private int driverId;
    private String driverName;
    private String carNumber;
    private int fleetId;
    private String driverPass;
    private String driverPhone;
    private boolean isAssign;

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

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
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

    public int getFleetId() {
        return fleetId;
    }

    public void setFleetId(int fleetId) {
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

    public boolean getAssign() {
        return isAssign;
    }

    public void setAssign(Boolean assign) {
        isAssign = assign;
    }
}
