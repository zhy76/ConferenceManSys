package com.conference.service.imp;

import com.conference.dao.PickUpDao;
import com.conference.entity.PickUp;
import com.conference.service.PickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PickUpService
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/3 14:44
 */
@Service("PickUpService")
public class PickUpServiceImp implements PickUpService {

    @Autowired
    private PickUpDao pickUpDao;

    @Override
    public int addPickUp(PickUp pickUp) {
        return pickUpDao.addPickUp(pickUp.getParticipantId(), pickUp.getDriverId(), pickUp.getTrainNumber(),
                pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
    }

    @Override
    public int deletePickUp(int participantId, int driverId) {
        return pickUpDao.deletePickUp(participantId, driverId);
    }

    @Override
    public List<PickUp> findAllPickUp() {
        return pickUpDao.findAllPickUp();
    }

    @Override
    public List<PickUp> findAllFleetPickUp(int fleetId) {
        return pickUpDao.findAllFleetPickUp(fleetId);
    }

    @Override
    public List<PickUp> findAllDriverPickUp(int driverId) {
        return pickUpDao.findAllDriverPickUp(driverId);
    }

    @Override
    public List<PickUp> findAllParticipantPickUp(int participantId) {
        return pickUpDao.findAllParticipantPickUp(participantId);
    }

    @Override
    public int updatePickUpByDriverId(PickUp pickUp) {
        return pickUpDao.updatePickUpByDriverId(pickUp.getParticipantId(), pickUp.getDriverId(),
                pickUp.getTrainNumber(), pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
    }

    @Override
    public int updatePickUpByParticipantId(PickUp pickUp) {
        return pickUpDao.updatePickUpByParticipantId(pickUp.getParticipantId(), pickUp.getDriverId(),
                pickUp.getTrainNumber(), pickUp.getToTime(), pickUp.getReturnTime(), pickUp.isFinishPickup());
    }
}
