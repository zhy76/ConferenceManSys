package com.conference.service.impl;

import com.conference.dao.*;
import com.conference.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/23 20:43
 **/
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private DriverDao driverDao;
    @Autowired
    private FleetDao fleetDao;
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private OrganizerDao organizerDao;
    @Autowired
    private ParticipantDao participantDao;

    @Override
    public void headPhotoUpload(String role,Integer roleId, String url) {
        if( role.equals("admin")){
            System.out.println("impl正在执行...");
            adminDao.updateAdminPhoto(url,roleId);
        }

    }
}
