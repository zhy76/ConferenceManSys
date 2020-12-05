package com.conference.mapper;

import com.conference.entity.Organizer;
import com.conference.entity.Participant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 22:05
 **/
@Mapper
@Repository
public interface OrganizerMapper {
    /**
     * 1、查询展示出所有注册的组织者
     * 2、查询出指定单位的组织者
     * 3、修改指定的组织者的账号信息
     * 4、删除指定ID的组织者
     */
    public List<Organizer> queryOrganizers();

    public Organizer queryOrganizerByOrganizerUnit(String organizerUnit);

    public int updateOrganizer(Organizer organizer);

    public int deleteOrganizer(Integer organizerId);
}
