package com.conference.entity;

/**
 * @ClassName: Fleet
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 18:37
 */
public class Fleet {
    private Integer fleetId;
    private String fleetName;
    private String fleetPass;
    private String fleetPhone;

    @Override
    public String toString() {
        return "Fleet{" +
                "fleetId=" + fleetId +
                ", fleetName='" + fleetName + '\'' +
                ", fleetPass='" + fleetPass + '\'' +
                ", fleetPhone='" + fleetPhone + '\'' +
                '}';
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getFleetPass() {
        return fleetPass;
    }

    public void setFleetPass(String fleetPass) {
        this.fleetPass = fleetPass;
    }

    public String getFleetPhone() {
        return fleetPhone;
    }

    public void setFleetPhone(String fleetPhone) {
        this.fleetPhone = fleetPhone;
    }
}
