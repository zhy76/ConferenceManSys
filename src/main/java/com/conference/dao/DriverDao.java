package com.conference.dao;

import com.conference.entity.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DriverMapper
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/1 17:50
 */
@Component
@Mapper
@Repository
@Service
public interface DriverDao {
/**
 * 	1. 查询所有司机
 * 	2. 查询一个车队的司机
 * 	3. 按照id查询司机
 * 	4. 增
 * 	5. 改
 *  6. 删
 */
    List<Driver> findAllDriver();

    List<Driver> findFleetAllDriver(@Param("fleetId") int fleetId);

    Driver findDriverById(@Param("driverId") int driverId);




    int updateDriver(@Param("driverId") int driverId, @Param("driverName")String driverName,
                      @Param("carNumber")String carNumber, @Param("fleetId")int fleetId,
                      @Param("driverPass")String driverPass, @Param("driverPhone")String driverPhone,
                      @Param("isAssign")boolean isAssign);


    int addDriver(@Param("driverName")String driverName, @Param("carNumber")String carNumber, @Param("fleetId")int fleetId, @Param("driverPass")String driverPass, @Param("driverPhone")String driverPhone);

    int deleteDriverById(@Param("driverId") int driverId);
}
