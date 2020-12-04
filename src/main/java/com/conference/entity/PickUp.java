package com.conference.entity;


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
    private String toTime;
    private String returnTime;
    private boolean isFinishPickup;

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

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public boolean isFinishPickup() {
        return isFinishPickup;
    }

    public void setFinishPickup(boolean finishPickup) {
        isFinishPickup = finishPickup;
    }
}
