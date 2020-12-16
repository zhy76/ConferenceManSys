package conferencemansys;

import com.conference.dao.ParticipantDao;
import com.conference.entity.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 13:42
 * @sno 6109118015
 */
@SpringBootTest
public class ParticipantDaoTest {
    @Autowired
    private ParticipantDao participantDao;

    /**
     * @Author 谢娇
     * @Description
     * @return
     **/
    @Test
    void contextLoads() {


//        System.out.println(participantDao.queryParticipantByParticipantName("刘涔宇"));
//
//        System.out.println(participantDao.addAParticipant(new Participant(4,"凌宸","学生","南昌大学","chen@qq.com","lingchen0522","18897991060","男","3254345412222211122")));
//
//        System.out.println(participantDao.queryParticipants());

//        System.out.println(participantDao.updateParticipant(new Participant(2,"abc","abc","abc","11111","11111","11111111111","男","1111111111111")));

//        System.out.println(participantDao.deleteParticipant(2));

        System.out.println(participantDao.queryParticipantByParticipantId(1));

        System.out.println(participantDao.queryParticipantByParticipantPhone("1111111"));
    }

}
