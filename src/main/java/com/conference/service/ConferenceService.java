package com.conference.service;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 10:28
 * @stuid 6109118041
 */

import com.alibaba.fastjson.JSONObject;
import com.conference.entity.Conference;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConferenceService {
    /**
     * 查看所有会议
     * @return 返回一个会议Conference列表
     **/
    public List<Conference> queryConferences();

    public List<JSONObject> selectAll(Integer pageNum, Integer pageSize);

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





    public int addConference(Conference conference);

    public Conference queryConferenceById(Integer conferenceId);
    public List<Conference> queryConferenceByFleetId(Integer fleetId);
    public List<Conference> queryConferenceByOrganizerId(Integer organizerId);
}