package com.conference.service.impl;

import com.conference.dao.PickUpDao;
import com.conference.entity.PickUp;
import com.conference.service.PickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PickUpService
 * @Description: TODO 外键约束
 * @Author: Lance
 * @Date: 2020/12/3 14:44
 */
@Service("PickUpService")
public class PickUpServiceImpl implements PickUpService {

    @Autowired
    private PickUpDao pickUpDao;

    @Override
    public int addPickUp(PickUp pickUp) {
        return pickUpDao.addPickUp(pickUp.getParticipantId(), pickUp.getDriverId(), pickUp.getConferenceId(),
                pickUp.getTrainNumber(), pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
    }

    @Override
    public int deletePickUp(Integer participantId, Integer driverId, Integer conferenceId) {
        return pickUpDao.deletePickUp(participantId, driverId, conferenceId);
    }

    @Override
    public int deletePickUpId(Integer pickUpId) {
        return pickUpDao.deletePickUpById(pickUpId);
    }

    @Override
    public List<PickUp> findAllPickUp() {
        return pickUpDao.findAllPickUp();
    }

    @Override
    public List<PickUp> findAllFleetPickUp(Integer fleetId) {
        return pickUpDao.findAllFleetPickUp(fleetId);
    }

    @Override
    public List<PickUp> findAllDriverPickUp(Integer driverId) {
        return pickUpDao.findAllDriverPickUp(driverId);
    }

    @Override
    public List<PickUp> findAllParticipantPickUp(Integer participantId) {
        return pickUpDao.findAllParticipantPickUp(participantId);
    }

    @Override
    public PickUp findPickUp(Integer participantId, Integer driverId, Integer conferenceId) {
        return pickUpDao.findPickUp(participantId, driverId, conferenceId);
    }

    @Override
    public PickUp findPickUpById(Integer pickUpId) {
        return pickUpDao.findPickUpById(pickUpId);
    }


//    @Override
//    public int updatePickUpByDriverId(PickUp pickUp) {
//        return pickUpDao.updatePickUpByDriverId(pickUp.getParticipantId(), pickUp.getDriverId(),
//                pickUp.getTrainNumber(), pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
//    }
//
//    @Override
//    public int updatePickUpByParticipantId(PickUp pickUp) {
//        return pickUpDao.updatePickUpByParticipantId(pickUp.getParticipantId(), pickUp.getDriverId(),
//                pickUp.getTrainNumber(), pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
//    }

    @Override
    public int updatePickUp(PickUp pickUp) {
        return pickUpDao.updatePickUp(pickUp.getParticipantId(), pickUp.getDriverId(), pickUp.getConferenceId(),
                pickUp.getTrainNumber(), pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
    }
}
