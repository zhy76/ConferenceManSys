package com.conference.service;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 10:28
 * @stuid 6109118041
 */

import com.conference.entity.Conference;

import java.util.List;

public interface ConferenceService {
    public List<Conference> queryConferences();

    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);
    public int addConference(Conference conference);

    public Conference queryConferenceById(Integer conferenceId);
}