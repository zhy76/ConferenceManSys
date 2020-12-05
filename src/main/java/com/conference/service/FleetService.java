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

    Fleet findFleetById(int fleetId);

    int deleteFleetById(int fleetId);

    int addFleet(Fleet fleet);
}
