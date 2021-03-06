package com.conference.entity;


/**
 * @ClassName: pickUp
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 18:50
 */

public class PickUp {

    private Integer pickUpId;
    private Integer participantId;
    private Integer fleetId;
    private Integer driverId;
    private Integer conferenceId;
    private String trainNumber;
    private String toTime;
    private String returnTime;
    private boolean isFinishPickup;

    public PickUp() {
    }

    public PickUp(Integer pickUpId, Integer participantId, Integer fleetId, Integer driverId, Integer conferenceId,
                  String trainNumber, String toTime, String returnTime, boolean isFinishPickup) {
        this.pickUpId = pickUpId;
        this.participantId = participantId;
        this.fleetId = fleetId;
        this.driverId = driverId;
        this.conferenceId = conferenceId;
        this.trainNumber = trainNumber;
        this.toTime = toTime;
        this.returnTime = returnTime;
        this.isFinishPickup = isFinishPickup;
    }

    @Override
    public String toString() {
        return "PickUp{" +
                "pickUpId=" + pickUpId +
                ", participantId=" + participantId +
                ", fleetId=" + fleetId +
                ", driverId=" + driverId +
                ", conferenceId=" + conferenceId +
                ", trainNumber='" + trainNumber + '\'' +
                ", toTime='" + toTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", isFinishPickup=" + isFinishPickup +
                '}';
    }

    public Integer getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(Integer pickUpId) {
        this.pickUpId = pickUpId;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
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
