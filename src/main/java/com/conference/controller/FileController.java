package com.conference.controller;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/23 19:53
 **/
import com.conference.service.FileUploadService;
import com.conference.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileUploadService  fileUploadService ;

    @PostMapping("/headPhotoUpload")
    public Result headPhotoUpload(@RequestParam("role") String role ,@RequestParam("roleId") Integer roleId, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        System.out.println(role);
        System.out.println(roleId);
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/headphoto";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        //用于查看路径是否正确
        System.out.println(realPath);

        if (file.isEmpty()) {
            return Result.error("文件为空!");
        }
        String fileName = file.getOriginalFilename();  // 获取上传文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 获取上传文件的后缀名
        fileName = UUID.randomUUID().toString()+ suffixName; // 生成新文件名

        try {
            File newFile = new File(realPath,fileName);
            file.transferTo(newFile);
            String url="headphoto/"+fileName;
            fileUploadService.headPhotoUpload(role,roleId,url);
            System.out.println("dadsadsa");
            return Result.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("失败");
        }
    }
}
