package com.conference.entity;


import java.sql.Timestamp;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 17:48
 * @sno 6109118015
 */

public class JoinConference {
    @Override
    public String toString() {
        return "JoinConference{" +
                "participantId=" + participantId +
                ", conferenceId=" + conferenceId +
                ", isPutup=" + isPutup +
                ", isPickup=" + isPickup +
                ", toTime='" + toTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", isConfirm=" + isConfirm +
                '}';
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Boolean getPutup() {
        return isPutup;
    }

    public void setPutup(Boolean putup) {
        isPutup = putup;
    }

    public Boolean getPickup() {
        return isPickup;
    }

    public void setPickup(Boolean pickup) {
        isPickup = pickup;
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

    public String getTrainNumber() {
        return trainNumber;
    }

    public JoinConference(Integer participantId, Integer conferenceId, Boolean isPutup, Boolean isPickup, String toTime, String returnTime, String trainNumber, Boolean isConfirm) {
        this.participantId = participantId;
        this.conferenceId = conferenceId;
        this.isPutup = isPutup;
        this.isPickup = isPickup;
        this.toTime = toTime;
        this.returnTime = returnTime;
        this.trainNumber = trainNumber;
        this.isConfirm = isConfirm;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Boolean getConfirm() {
        return isConfirm;
    }

    public void setConfirm(Boolean confirm) {
        isConfirm = confirm;
    }

    private Integer participantId;
    private Integer conferenceId;
//    private Integer isPutup; //是否需要住宿
    private Boolean isPutup;
    private Boolean isPickup; //是否需要接送
    private String toTime; //参会往时间
    private String returnTime; //参会返时间
    private String trainNumber; //车次或航班号
    private Boolean isConfirm;//是否已确认
}
