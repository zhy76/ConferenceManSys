package com.conference.service;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/24 0:05
 * @sno 6109118015
 */

public interface FileUploadService {
        /**
         * 上传头像功能
         * role=角色，roleId=角色id,url=图片地址
         */
        public void headPhotoUpload(String role,Integer roleId, String url);
}
