package com.conference.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: Fleet
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 18:37
 */
public class Fleet {

    private Integer fleetId;
    @NotNull(message = "车队名必须存在")
    private String fleetName;

    @NotNull(message = "密码必须存在")
    @Length(min = 6, max = 26, message = "密码长度应该在6至26之间")
    @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符")
    private String fleetPass;

    @NotNull(message = "电话号码必须存在")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式")
    private String fleetPhone;

    private String fleetPhoto = "headphoto/default.jpg";

    public Fleet() {
    }

    public Fleet(Integer fleetId, @NotNull(message = "车队名必须存在") String fleetName, @NotNull(message = "密码必须存在") @Length(min = 6, max = 26, message = "密码长度应该在6至26之间") @Pattern(regexp = "^[^\\s]+$", message = "密码不能包含空白字符") String fleetPass, @NotNull(message = "电话号码必须存在") @Pattern(regexp = "^1[3456789]\\d{9}$", message = "请输入正确的电话号码格式") String fleetPhone, String fleetPhoto) {
        this.fleetId = fleetId;
        this.fleetName = fleetName;
        this.fleetPass = fleetPass;
        this.fleetPhone = fleetPhone;
        // 要求给定的头像路径不能为空
        if (fleetPhoto != null && !"".equals(fleetPhoto)) {
            this.fleetPhoto = fleetPhoto;
        }
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public String getFleetPass() {
        return fleetPass;
    }

    public String getFleetPhone() {
        return fleetPhone;
    }

    public String getFleetPhoto() {
        return fleetPhoto;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public void setFleetPass(String fleetPass) {
        this.fleetPass = fleetPass;
    }

    public void setFleetPhone(String fleetPhone) {
        this.fleetPhone = fleetPhone;
    }

    public void setFleetPhoto(String fleetPhoto) {
        // 要求给定的商品封面路径不能为空
        if (fleetPhoto != null && !"".equals(fleetPhoto)) {
            this.fleetPhoto = fleetPhoto;
        }
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "fleetId=" + fleetId +
                ", fleetName='" + fleetName + '\'' +
                ", fleetPass='" + fleetPass + '\'' +
                ", fleetPhone='" + fleetPhone + '\'' +
                ", fleetPhoto='" + fleetPhoto + '\'' +
                '}';
    }
}
