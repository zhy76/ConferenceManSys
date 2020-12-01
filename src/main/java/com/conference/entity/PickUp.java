package com.conference.entity;


import java.util.Date;

/**
 * @ClassName: pickUp
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 18:50
 */

public class PickUp {
    private int participantId;
    private int driverId;
    private String trainNumber;
    private Date toTime;
    private Date returnTime;
    private Boolean isFinishPickup;

    @Override
    public String toString() {
        return "pickUp{" +
                "participantId=" + participantId +
                ", driverId=" + driverId +
                ", trainNumber='" + trainNumber + '\'' +
                ", toTime=" + toTime +
                ", returnTime=" + returnTime +
                ", isFinishPickup=" + isFinishPickup +
                '}';
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Boolean getFinishPickup() {
        return isFinishPickup;
    }

    public void setFinishPickup(Boolean finishPickup) {
        isFinishPickup = finishPickup;
    }
}
