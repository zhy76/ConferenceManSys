package com.conference.service;

import com.conference.entity.PickUp;

import java.util.List;

/**
 * @ClassName: PickUpService
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/5 17:22
 */
public interface PickUpService {
    int addPickUp(PickUp pickUp);
    /**删*/
    int deletePickUp(Integer participantId, Integer driverId);

    /**查*/
    List<PickUp> findAllPickUp();

    //查询一个车队的接送信息
    List<PickUp> findAllFleetPickUp(Integer fleetId);

    // 查询一个司机的接送信息
    List<PickUp> findAllDriverPickUp(Integer driverId);

    // 查询个人的接送信息
    List<PickUp> findAllParticipantPickUp(Integer participantId);

    // 改
    int updatePickUpByDriverId(PickUp pickUp);

    // 改
    int updatePickUpByParticipantId(PickUp pickUp);

}
