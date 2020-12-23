package com.conference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class UploadApi {
    @PostMapping("/images")
    public int singleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return 0;
        }
        try {
            System.out.print("file:"+file);
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static/avatars/"+file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
//@RestController
//@RequestMapping("/upload")
//public class UploadController {
//    @Autowired
//    private MeetingService meetingService;
//    @PostMapping("/upload/image/{meetingId}")
//    public String uploadImage(@PathVariable String meetingId, HttpServletRequest request, @RequestParam("img") MultipartFile multipartFile) throws IOException {
//        ServletContext context=request.getServletContext();
//        String realPath = context.getRealPath("/upload/images");
//        File file = new File(realPath);
//        if(!file.isDirectory()){
//            file.mkdirs();
//        }
//        String oldName = multipartFile.getOriginalFilename();
//        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
//        try {
//            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
//            System.out.println(file.getAbsolutePath() + File.separator + newName);
//            multipartFile.transferTo(newFile);
//            String filePath="/upload/images/"+newName;
//            meetingService.saveImg(meetingId,filePath);
//            return "redirect:/user/updateMeeting/"+meetingId;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/meetingInfo/"+meetingId;
//
//    }
//}
