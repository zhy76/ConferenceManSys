package com.conference.entity;


import java.sql.Timestamp;

/**
 * @Description
 * @Author л ��
 * @Date 2020/12/5 17:48
 * @sno 6109118015
 */

public class JoinConference {
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

    public Boolean getIsPutup() {
        return isPutup;
    }

    public void setIsPutup(Boolean isPutup) {
        this.isPutup = isPutup;
    }

    public Boolean getPickup() {
        return isPickup;
    }

    public JoinConference() {
    }

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
                '}';
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

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    private Integer participantId;
    private Integer conferenceId;
//    private Integer isPutup; //�Ƿ���Ҫס��
    private Boolean isPutup;
    private Boolean isPickup; //�Ƿ���Ҫ����
    private String toTime; //�λ���ʱ��
    private String returnTime; //�λ᷵ʱ��
    private String trainNumber; //���λ򺽰��
}