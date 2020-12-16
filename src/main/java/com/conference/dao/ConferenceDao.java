package com.conference.dao;

import com.conference.entity.Conference;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 20:42
 * @sno 6109118015
 */
@Mapper
@Repository
public interface ConferenceDao {

    /**
     * 1、展示所有会议---- 查询所有会议
     * 2、删除会议
     * 3、更改会议信息
     */
    public List<Conference> queryConferences();

    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);

    /**
     * 4、通过会议id查询会议（显示会议详情）
     *
     * @return
     **/
    public Conference queryConferenceByConferenceId(Integer conferenceId);



}
