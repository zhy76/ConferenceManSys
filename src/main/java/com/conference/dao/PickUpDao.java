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
 * 	1. 查询所有接送
 * 	2. 查询已完成的接送
 * 	3. 查询未完成的接送
 * 	4. 查询一个车队的接送信息
 * 	5. 查询一个司机的接送信息
 * 	6. 查询个人的接送信息
 * 	7. 删
 * 	8. 改
 *  9. 增
 */

    /**增*/
    int addPickUp(@Param("participantId")int participantId, @Param("driverId")int driverId,
                  @Param("conferenceId")int conferenceId,
                  @Param("trainNumber")String trainNumber, @Param("toTime") String toTime,
                  @Param("returnTime") String returnTime, @Param("isFinishPickup")boolean isFinishPickup);
    /**精确删*/
    int deletePickUp(@Param("participantId")int participantId,
                     @Param("driverId")int driverId,
                     @Param("conferenceId")int conferenceId);

    int deletePickUpById(@Param("pickUpId")int pickUpId);
    /**查*/
    List<PickUp> findAllPickUp();

    //查询一个车队的接送信息
    List<PickUp> findAllFleetPickUp(@Param("fleetId")int fleetId);

    // 查询一个司机的接送信息
    List<PickUp> findAllDriverPickUp(@Param("driverId")int driverId);

    /**
     * TODO
     * @param conferenceId
     * @return
     */
    List<PickUp> findAllConferencePickUp(@Param("conferenceId")int conferenceId);
    List<PickUp> findAllDriverDonePickUp(@Param("driverId")int driverId);
    List<PickUp> findAllParticipantDonePickUp(@Param("participantId")int participantId);

    // 查询个人的接送信息
    List<PickUp> findAllParticipantPickUp(@Param("participantId")int participantId);

    // 精确查
    PickUp findPickUp(@Param("participantId")int participantId,
                      @Param("driverId")int driverId,
                      @Param("conferenceId")int conferenceId);

    PickUp findPickUpById(@Param("pickUpId")int pickUpId);

    // 精确修改
    int updatePickUp(@Param("pickUpId")int pickUpId, @Param("participantId")int participantId,
                     @Param("fleetId")int fleetId, @Param("driverId")int driverId,
                     @Param("conferenceId")int conferenceId, @Param("trainNumber")String trainNumber,
                     @Param("toTime")String toTime, @Param("returnTime")String returnTime,
                     @Param("isFinishPickup")boolean isFinishPickup);

    PickUp findPickUpByParticipantIdAndConferenceId(@Param("participantId")int participantId,
                                                    @Param("conferenceId")int conferenceId);
}
