package com.conference.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Organizer {
    private Integer organizerId; //组织单位ID
    private String organizerEmail; //组织单位邮箱
    @NotNull(message = "组织名必须存在")
    private String organizerUnit; //组织单位

    @NotNull(message = "密码必须存在")
    @Length(min = 6, max = 26, message = "密码长度应该在6至26之间")
    @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符")
    private String organizerPass; //组织单位密码

    @NotNull(message = "电话号码必须存在")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式")
    private String organizerPhone; //组织单位电话

    public Organizer(Integer organizerId, String organizerEmail, @NotNull(message = "组织名必须存在") String organizerUnit, @NotNull(message = "密码必须存在") @Length(min = 6, max = 26, message = "密码长度应该在6至26之间") @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符") String organizerPass, @NotNull(message = "电话号码必须存在") @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式") String organizerPhone) {
        this.organizerId = organizerId;
        this.organizerEmail = organizerEmail;
        this.organizerUnit = organizerUnit;
        this.organizerPass = organizerPass;
        this.organizerPhone = organizerPhone;
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "organizerId=" + organizerId +
                ", organizerEmail='" + organizerEmail + '\'' +
                ", organizerUnit='" + organizerUnit + '\'' +
                ", organizerPass='" + organizerPass + '\'' +
                ", organizerPhone='" + organizerPhone + '\'' +
                '}';
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public String getOrganizerUnit() {
        return organizerUnit;
    }

    public void setOrganizerUnit(String organizerUnit) {
        this.organizerUnit = organizerUnit;
    }

    public String getOrganizerPass() {
        return organizerPass;
    }

    public void setOrganizerPass(String organizerPass) {
        this.organizerPass = organizerPass;
    }

    public String getOrganizerPhone() {
        return organizerPhone;
    }

    public void setOrganizerPhone(String organizerPhone) {
        this.organizerPhone = organizerPhone;
    }
}