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
/**
 * 	1. 查询所有车队
 * 	2. 按id查询车队
 * 	3. 添加车队
 * 	4. 改
 *  5. 删
 */

    List<Fleet> findAllFleet();

    Fleet findFleetById(@Param("fleetId") int fleetId);


    int deleteFleetById(@Param("fleetId") int fleetId);

    int addFleet(@Param("fleetName")String fleetName, @Param("fleetPass")String fleetPass, @Param("fleetPhone")String fleetPhone);


}
