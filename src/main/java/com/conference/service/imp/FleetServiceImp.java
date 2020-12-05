package com.conference.service.imp;

import com.conference.dao.FleetDao;
import com.conference.entity.Fleet;
import com.conference.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: FleetService
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 21:16
 */
@Service("FleetService")
public class FleetServiceImp implements FleetService {

    @Autowired
    private FleetDao fleetDao;


    @Override
    public List<Fleet> findAllFleet() {
        return fleetDao.findAllFleet();
    }

    @Override
    public Fleet findFleetById(int fleetId) {
        return fleetDao.findFleetById(fleetId);
    }

    @Override
    public int deleteFleetById(int fleetId) {
        return fleetDao.deleteFleetById(fleetId);
    }

    @Override
    public int addFleet(Fleet fleet) {
        return fleetDao.addFleet(fleet.getFleetName(), fleet.getFleetPass(), fleet.getFleetPhone());
    }
}
