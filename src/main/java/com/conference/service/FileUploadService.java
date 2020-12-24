package com.conference.service;

public interface FileUploadService {
    /**
     * 上传头像功能
     * role=角色，roleId=角色id,url=图片地址
     */
    public void headPhotoUpload(String role,Integer roleId, String url);

}
