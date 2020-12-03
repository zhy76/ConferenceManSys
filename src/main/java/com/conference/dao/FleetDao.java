package com.conference.dao;

import com.conference.entity.Fleet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: FleetMapper
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 18:39
 */
@Component
@Mapper
public interface FleetDao {
    /**查询所有车队*/
    List<Fleet> findAllFleet();
    /**按id查询车队*/
    Fleet findFleetById(@Param("fleetId") int fleetId);
    /**删*/
    int deleteFleetById(@Param("fleetId") int fleetId);
    /**增*/
    int addFleet(@Param("fleetName")String fleetName, @Param("fleetPass")String fleetPass, @Param("fleetPhone")String fleetPhone);

}
