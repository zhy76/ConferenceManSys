package com.conference.service;

import com.conference.entity.Fleet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: FleetService
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/5 17:21
 */
public interface FleetService {
    List<Fleet> findAllFleet();

    Fleet findFleetById(Integer fleetId);

    Fleet findFleetByPhone(String fleetPhone);

    int deleteFleetById(Integer fleetId);

    int addFleet(Fleet fleet);

    int updateFleet(Fleet fleet);
}
