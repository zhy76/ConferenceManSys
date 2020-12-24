package com.conference.service;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/23 20:37
 **/
public interface FileUploadService {
    /**
     * 上传头像功能
     * role=角色，roleId=角色id,url=图片地址
     */
    public void headPhotoUpload(String role, Integer roleId, String url);

}
