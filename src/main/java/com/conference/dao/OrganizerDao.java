package com.conference.dao;

import com.conference.entity.Driver;
import com.conference.entity.Organizer;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:42
 * @stuid 6109118041
 */
@Mapper
@Repository
public interface OrganizerDao {
    /**
     *更改组织者信息
     */
    int updateOrganizer(@Param("organizerId") Integer organizerId, @Param("organizerEmail") String organizerEmail,
                     @Param("organizerUnit") String organizerUnit, @Param("organizerPass") String organizerPass,
                     @Param("organizerPhone") String organizerPhone);

    /**
     * 查询所有组织者
     */
    List<Organizer> findAllOrganizer();

    /**
     * 查询指定id组织者
     */
    Organizer findOrganizerById(@Param("organizerId") Integer organizerId);

    /**
     * 按照电话查询司机
     */
    Organizer findOrganizerByPhone(@Param("organizerPhone") String organizerPhone);

    /**
     * 增加组织者
     */
    int addOrganizer(@Param("organizerId") Integer organizerId, @Param("organizerEmail") String organizerEmail,
                  @Param("organizerUnit") String organizerUnit, @Param("organizerPass") String organizerPass,
                  @Param("organizerPhone") String organizerPhone);

    /**
     * 删除组织者
     */
    int deleteOrganizerById(@Param("organizerId") Integer driverId);

}
