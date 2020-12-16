package com.conference.service;

import com.conference.entity.Conference;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 22:29
 * @sno 6109118015
 */

public interface ConferenceService {
    /**
     * 查看所有会议
     * @return 返回一个会议Conference列表
     **/
    public List<Conference> queryConferences();

    /**
     * 修改会议
     * @return 返回受影响行数
     **/
    public int updateConference(Conference conference);


    /**
     * 删除会议
     * @return 返回受影响行数
     **/
    public int deleteConference(Integer conferenceId);

    /**
     * 通过会议id查询会议
     * @return 返回一个会议Conference
     **/
    public Conference queryConferenceByConferenceId(Integer conferenceId);


}
