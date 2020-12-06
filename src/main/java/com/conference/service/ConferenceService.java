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

    public List<Conference> queryConferences();

    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);

    public Conference queryConferenceByConferenceId(Integer conferenceId);


}
