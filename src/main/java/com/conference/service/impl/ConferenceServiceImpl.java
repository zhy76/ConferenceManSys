package com.conference.service.impl;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 10:29
 * @stuid 6109118041
 */
import com.conference.entity.Conference;
import com.conference.dao.ConferenceDao;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceDao conferenceDao;

    @Override
    public List<Conference> queryConferences() {
        return conferenceDao.queryConferences();
    }

    @Override
    public int updateConference(Conference conference) {
        return conferenceDao.updateConference(conference);
    }

    @Override
    public int deleteConference(Integer conferenceId) {
        return conferenceDao.deleteConference(conferenceId);
    }

    @Override
    public int addConference(Conference conference) {
        return conferenceDao.addConference(conference);
    }

    @Override
    public Conference queryConferenceById(Integer conferenceId) {
        return conferenceDao.queryConferenceById(conferenceId);
    }

    @Override
    public Conference queryConferenceByConferenceId(Integer conferenceId) {
        return conferenceDao.queryConferenceByConferenceId(conferenceId);
    }
}
