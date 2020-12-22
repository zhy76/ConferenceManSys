package com.conference.dao;

import com.conference.entity.PickUp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: PickUpMapper
 * @Description: TODO 1
 * @Author: Lance
 * @Date: 2020/12/1 18:53
 */
@Component
@Mapper
public interface PickUpDao {
/**
 *
 * 	1. ��ѯ���н���
 * 	2. ��ѯ����ɵĽ���
 * 	3. ��ѯδ��ɵĽ���
 * 	4. ��ѯһ�����ӵĽ�����Ϣ
 * 	5. ��ѯһ��˾���Ľ�����Ϣ
 * 	6. ��ѯ���˵Ľ�����Ϣ
 * 	7. ɾ
 * 	8. ��
 *  9. ��
 */

    /**��*/
    int addPickUp(@Param("participantId")int participantId, @Param("driverId")int driverId,
                  @Param("conferenceId")int conferenceId,
                  @Param("trainNumber")String trainNumber, @Param("toTime") String toTime,
                  @Param("returnTime") String returnTime, @Param("isFinishPickup")boolean isFinishPickup);
    /**��ȷɾ*/
    int deletePickUp(@Param("participantId")int participantId,
                     @Param("driverId")int driverId,
                     @Param("conferenceId")int conferenceId);

    int deletePickUpById(@Param("pickUpId")int pickUpId);
    /**��*/
    List<PickUp> findAllPickUp();

    //��ѯһ�����ӵĽ�����Ϣ
    List<PickUp> findAllFleetPickUp(@Param("fleetId")int fleetId);

    // ��ѯһ��˾���Ľ�����Ϣ
    List<PickUp> findAllDriverPickUp(@Param("driverId")int driverId);

    /**
     * TODO
     * @param conferenceId
     * @return
     */
    List<PickUp> findAllConferencePickUp(@Param("conferenceId")int conferenceId);
    List<PickUp> findAllDriverDonePickUp(@Param("driverId")int driverId);
    List<PickUp> findAllParticipantDonePickUp(@Param("participantId")int participantId);

    // ��ѯ���˵Ľ�����Ϣ
    List<PickUp> findAllParticipantPickUp(@Param("participantId")int participantId);

    // ��ȷ��
    PickUp findPickUp(@Param("participantId")int participantId,
                      @Param("driverId")int driverId,
                      @Param("conferenceId")int conferenceId);

    PickUp findPickUpById(@Param("pickUpId")int pickUpId);

    // ��ȷ�޸�
    int updatePickUp(@Param("pickUpId")int pickUpId, @Param("participantId")int participantId,
                     @Param("fleetId")int fleetId, @Param("driverId")int driverId,
                     @Param("conferenceId")int conferenceId, @Param("trainNumber")String trainNumber,
                     @Param("toTime")String toTime, @Param("returnTime")String returnTime,
                     @Param("isFinishPickup")boolean isFinishPickup);

    PickUp findPickUpByParticipantIdAndConferenceId(@Param("participantId")int participantId,
                                                    @Param("conferenceId")int conferenceId);
}