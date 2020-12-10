package com.conference.service.Impl;

import com.conference.dao.JoinConferenceDao;
import com.conference.entity.JoinConference;
import com.conference.service.JoinConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 23:27
 * @sno 6109118015
 */
@Service("joinConferenceService")
public class JoinConferenceServiceImpl implements JoinConferenceService {

    @Autowired
    private JoinConferenceDao joinConferenceDao;
    @Override
    public int joinAConference(JoinConference joinConference) {
        return joinConferenceDao.joinAConference(joinConference);
    }

    @Override
    public int cancelAJoinedConferenceById(Integer participantId, Integer conferenceId) {
        return joinConferenceDao.cancelAJoinedConferenceById(participantId,conferenceId);
    }
}
