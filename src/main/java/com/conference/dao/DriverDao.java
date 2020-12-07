package com.conference.dao;

import com.conference.entity.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: DriverMapper
 * @Description:
 * @Author: Lance
 * @Date: 2020/12/1 17:50
 */
@Component
@Mapper
public interface DriverDao {

    /**
     * 查询所有司机
     */
    List<Driver> findAllDriver();

    /**
     * 查询一个车队的司机
     */
    List<Driver> findFleetAllDriver(@Param("fleetId") Integer fleetId);

    /**
     * 按照id查询司机
     */
    Driver findDriverById(@Param("driverId") Integer driverId);
    /**
     * 按照电话查询司机
     */
    Driver findDriverByPhone(@Param("driverPhone") String driverPhone);

    /**
     * 改
     */
    int updateDriver(@Param("driverId") Integer driverId, @Param("driverName") String driverName,
                     @Param("carNumber") String carNumber, @Param("fleetId") Integer fleetId,
                     @Param("driverPass") String driverPass, @Param("driverPhone") String driverPhone,
                     @Param("isAssign") Boolean isAssign);

    /**
     * 增
     */
    int addDriver(@Param("driverName") String driverName, @Param("carNumber") String carNumber,
                  @Param("fleetId") Integer fleetId, @Param("driverPass") String driverPass,
                  @Param("driverPhone") String driverPhone);

    /**
     * 删
     */
    int deleteDriverById(@Param("driverId") Integer driverId);

    /**
     * 修改司机的状态isAssign
     */
    int updateDriverIsAssign(@Param("driverId") Integer driverId, @Param("isAssign") Boolean isAssign);


}
