package com.conference;

import com.conference.dao.ConferenceDao;
import com.conference.dao.OrganizerDao;
import com.conference.entity.Conference;
import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import com.conference.service.impl.OrganizerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:54
 * @stuid 6109118041
 */
@SpringBootTest
public class OrganizerDaoTest {
    @Autowired
    private OrganizerDao organizerDao;
    @Autowired
    private OrganizerService os;
    @Autowired
    private OrganizerServiceImpl oimpl;
    @Test
    public void contextLoads(){
        System.out.println(organizerDao.updateOrganizer(1,"132141","信工","22222","22222"));
        //System.out.println(organizerDao.findOrganizerById(4));
        //System.out.println(organizerDao.addOrganizer(null,"654646","左海余1","123","123456"));
//        Organizer organizer=new Organizer(null,"65464","左海余2","123","12345");
       //System.out.println(oimpl.addOrganizer(organizer));
//        System.out.println(driverServiceImp.findDriverByPhone("12121212"));
      System.out.println(os.findAllOrganizer());
//        System.out.println(fleetServiceImp.findFleetById(1));
     // System.out.println(organizerDao.deleteOrganizerById(4));
////        System.out.println(fleetServiceImp.addFleet());
//        System.out.println(fleetServiceImp.deleteFleetById(1));
    }
}
