package com.conference.service;

import com.conference.entity.Conference;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 23:02
 **/
public interface ConferenceService {
    public List<Conference> queryConferences();

    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);

    public Conference queryConferenceById(Integer conferenceId);
}
