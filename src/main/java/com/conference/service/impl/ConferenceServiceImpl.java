package com.conference.service.impl;

import com.conference.entity.Conference;
import com.conference.mapper.ConferenceMapper;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 23:30
 **/
@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceMapper conferenceMapper;

    @Override
    public List<Conference> queryConferences() {
        return conferenceMapper.queryConferences();
    }

    @Override
    public int updateConference(Conference conference) {
        return conferenceMapper.updateConference(conference);
    }

    @Override
    public int deleteConference(Integer conferenceId) {
        return conferenceMapper.deleteConference(conferenceId);
    }
}
