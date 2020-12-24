package com.conference.entity;


/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/3 18:32
 * @sno 6109118015
 */

public class Participant {

    private Integer participantId;
    private String participantName; //姓名
    private String participantJob; //参加者职业：例如教授、学生等
    private String participantWorkUnit; //参加者工作单位： xx学校、研究院等等
    private String participantEmail;
    private String participantPass;
    private String participantPhone;
    private String participantSex;
    private String participantIdCard; //参加者身份证
    private String participantPhoto = "/headphoto/participantDefault.jpg";

    public Participant() {
    }

    public Participant(Integer participantId, String participantName, String participantJob, String participantWorkUnit, String participantEmail, String participantPass, String participantPhone, String participantSex, String participantIdCard, String participantPhoto) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.participantJob = participantJob;
        this.participantWorkUnit = participantWorkUnit;
        this.participantEmail = participantEmail;
        this.participantPass = participantPass;
        this.participantPhone = participantPhone;
        this.participantSex = participantSex;
        this.participantIdCard = participantIdCard;
        this.participantPhoto = participantPhoto;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantJob() {
        return participantJob;
    }

    public void setParticipantJob(String participantJob) {
        this.participantJob = participantJob;
    }

    public String getParticipantWorkUnit() {
        return participantWorkUnit;
    }

    public void setParticipantWorkUnit(String participantWorkUnit) {
        this.participantWorkUnit = participantWorkUnit;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public String getParticipantPass() {
        return participantPass;
    }

    public void setParticipantPass(String participantPass) {
        this.participantPass = participantPass;
    }

    public String getParticipantPhone() {
        return participantPhone;
    }

    public void setParticipantPhone(String participantPhone) {
        this.participantPhone = participantPhone;
    }

    public String getParticipantSex() {
        return participantSex;
    }

    public void setParticipantSex(String participantSex) {
        this.participantSex = participantSex;
    }

    public String getParticipantIdCard() {
        return participantIdCard;
    }

    public void setParticipantIdCard(String participantIdCard) {
        this.participantIdCard = participantIdCard;
    }

    public String getParticipantPhoto() {
        return participantPhoto;
    }

    public void setParticipantPhoto(String participantPhoto) {
        this.participantPhoto = participantPhoto;
    }
}