package com.conference.service.impl;

import com.conference.entity.Conference;
import com.conference.dao.ConferenceDao;
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
    public Conference queryConferenceByConferenceId(Integer conferenceId) {
        return conferenceDao.queryConferenceByConferenceId(conferenceId);
    }

    @Override
    public int addConference(Conference conference) {
        return 0;
    }

    @Override
    public List<Conference> queryConferenceByOrganizerId(Integer organizerId) { //用于修改
        return conferenceDao.queryConferenceByOrganizerId(organizerId);
    }
}
