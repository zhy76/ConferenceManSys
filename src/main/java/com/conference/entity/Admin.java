package com.conference.entity;


public class Admin {
    private Integer adminId; //管理员ID
    private String adminName; //管理员姓名
    private String adminPass; //管理员密码
    private String adminAccount; //管理员账号
    private String adminPhoto = "/headphoto/default.jpg"; //管理员照片地址

    public Admin() {
    }

    public Admin(Integer adminId, String adminName, String adminPass, String adminAccount, String adminPhoto) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.adminAccount = adminAccount;
        this.adminPhoto = adminPhoto;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPass='" + adminPass + '\'' +
                ", adminAccount='" + adminAccount + '\'' +
                ", adminPhoto='" + adminPhoto + '\'' +
                '}';
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public void setAdminPhoto(String adminPhoto) {
        this.adminPhoto = adminPhoto;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public String getAdminPhoto() {
        return adminPhoto;
    }
}
