package com.conference.service.Impl;

import com.conference.dao.ConferenceDao;
import com.conference.entity.Conference;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 22:31
 * @sno 6109118015
 */
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
}