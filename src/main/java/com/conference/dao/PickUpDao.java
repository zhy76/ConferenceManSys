package com.conference.dao;

import com.conference.entity.PickUp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: PickUpMapper
 * @Description: TODO
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

    // 查询个人的接送信息
    List<PickUp> findAllParticipantPickUp(@Param("participantId")int participantId);

    // 精确查
    PickUp findPickUp(@Param("participantId")int participantId,
                      @Param("driverId")int driverId,
                      @Param("conferenceId")int conferenceId);

    PickUp findPickUpById(@Param("pickUpId")int pickUpId);
    // 改
//    int updatePickUpByDriverId(@Param("participantId")int participantId, @Param("driverId")int driverId,
//                               @Param("conferenceId")int conferenceId,
//                               @Param("trainNumber")String trainNumber, @Param("toTime")String toTime,
//                               @Param("returnTime")String returnTime, @Param("isFinishPickup")boolean isFinishPickup);
//
//    // 改
//    int updatePickUpByParticipantId(@Param("participantId")int participantId, @Param("driverId")int driverId,
//                                    @Param("conferenceId")int conferenceId,
//                                    @Param("trainNumber")String trainNumber, @Param("toTime")String toTime,
//                                    @Param("returnTime")String returnTime, @Param("isFinishPickup")boolean isFinishPickup);

    // 精确修改
    int updatePickUp(@Param("participantId")int participantId, @Param("driverId")int driverId,
                                               @Param("conferenceId")int conferenceId,
                                    @Param("trainNumber")String trainNumber, @Param("toTime")String toTime,
                                    @Param("returnTime")String returnTime, @Param("isFinishPickup")boolean isFinishPickup);
}
