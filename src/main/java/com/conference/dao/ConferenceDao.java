package com.conference.dao;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 10:20
 * @stuid 6109118041
 */
import com.conference.entity.Conference;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConferenceDao {
    /**
     * 1、展示所有会议---- 查询所有会议
     * 2、删除会议
     * 3、更改会议信息
     * 4、根据id查询会议
     */

    public List<Conference> queryConferences();

    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);
    public int addConference(Conference conference);
    public Conference queryConferenceByConferenceId(Integer conferenceId);//统一
    public List<Conference> queryConferenceByOrganizerId(Integer organizerId);
}