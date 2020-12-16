package com.conference.dao;

import com.conference.entity.Organizer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:42
 * @stuid 6109118041
 */
@Mapper
@Repository
public interface OrganizerDao {
    public int updateOrganizer(Organizer organizer);
}
