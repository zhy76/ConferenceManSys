package conferencemansys;

import com.conference.ConferenceApplication;
import com.conference.controller.ConferenceController;
import com.conference.dao.ConferenceDao;
import com.conference.dao.DriverDao;
import com.conference.dao.HotelDao;
import com.conference.dao.ParticipantDao;
import com.conference.entity.Conference;
import com.conference.entity.Participant;
import com.conference.service.ConferenceService;
import com.conference.service.DriverService;
import com.conference.service.impl.DriverServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ConferenceApplication.class)
class ConferencemansysApplicationTests {
    @Autowired
    ConferenceDao conferenceDao;
    @Autowired
    ConferenceService conferenceService;
    @Autowired
    ConferenceController conferenceController;
    @Test
    void contextLoads() {
        System.out.println(conferenceController.showConferences());
        System.out.println(conferenceController.showConference(1));
//        System.out.println(conferenceService.queryConferences());
//        System.out.println(conferenceDao.queryConferences());
    }

}
