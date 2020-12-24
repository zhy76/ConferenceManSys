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
    /**
     * 1、展示所有会议---- 查询所有会议
     * 2、删除会议
     * 3、更改会议信息
     */
    public List<Conference> queryConferences();

    /**
     * 4、通过会议id查询会议（显示会议详情）
     *
     * @return
     **/
    public Conference queryConferenceByConferenceId(Integer conferenceId);


    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);
    public int addConference(Conference conference);
    public List<Conference> queryConferenceByOrganizerId(Integer organizerId);
    public Conference queryConferenceById(Integer conferenceId);



    public List<Conference> queryConferenceByFleetId(Integer fleetId);
}
