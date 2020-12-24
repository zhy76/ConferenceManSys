package com.conference.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Hotel {
    private int hotelId;

    @NotNull(message = "姓名必须存在")
    @Length(min = 1, max = 10, message = "姓名长度应该在1至10之间")
    @Pattern(regexp = "^[^\\s]+$", message = "酒店名不能包含空白字符")
    private String hotelName ;

    private String hotelLocation;

    @NotNull(message = "电话号码必须存在")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式")
    private String hotelPhone;

    @NotNull(message = "密码必须存在")
    @Length(min = 6, max = 26, message = "密码长度应该在6至26之间")
    @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符")
    private String hotelPass;

    private String hotelInfo;
    private String hotelPhoto="headphoto/default.jpg";
    public Hotel() {
    }

    public Hotel(int hotelId, @NotNull(message = "姓名必须存在") @Length(min = 1, max = 10, message = "姓名长度应该在1至10之间") @Pattern(regexp = "^[^\\s]+$", message = "用户名不能包含空白字符") String hotelName, String hotelLocation, @NotNull(message = "电话号码必须存在") @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式") String hotelPhone, @NotNull(message = "密码必须存在") @Length(min = 6, max = 26, message = "密码长度应该在6至26之间") @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符") String hotelPass, String hotelInfo) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.hotelPhone = hotelPhone;
        this.hotelPass = hotelPass;
        this.hotelInfo = hotelInfo;
    }



    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelPass() {
        return hotelPass;
    }

    public void setHotelPass(String hotelPass) {
        this.hotelPass = hotelPass;
    }

    public String getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public String getHotelPhoto() {
        return hotelPhoto;
    }

    public void setHotelPhoto(String hotelPhoto) {
        this.hotelPhoto = hotelPhoto;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelLocation='" + hotelLocation + '\'' +
                ", hotelPhone='" + hotelPhone + '\'' +
                ", hotelPass='" + hotelPass + '\'' +
                ", hotelInfo='" + hotelInfo + '\'' +
                ", hotelPhoto='" + hotelPhoto + '\'' +
                '}';
    }
}
