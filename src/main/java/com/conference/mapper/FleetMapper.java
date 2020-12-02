package com.conference.mapper;

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
public interface FleetMapper {
/**
 * 	1. 查询所有车队
 * 	2. 按id查询车队
 * 	3. 添加车队
 * 	4. 修改车队信息
 *  5. 删除车队
 */

    List<Fleet> findAllFleet();

    void deleteFleetById(@Param("fleetId") int fleetId);
}
