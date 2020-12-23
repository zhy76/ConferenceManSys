package com.conference.service.impl;

import com.conference.dao.JoinConferenceDao;
import com.conference.entity.JoinConference;
import com.conference.service.JoinConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 左海余
     * 确定会议
     * @param participantId
     * @param conferenceId
     * @return
     */
    @Override
    public int confirmAJoinedConferenceById(Integer participantId, Integer conferenceId) {
        return joinConferenceDao.confirmAJoinedConferenceById(participantId,conferenceId);
    }

    @Override
    public List<JoinConference> queryConferenceByParticipantId(Integer participantId) {
        return joinConferenceDao.queryConferenceByParticipantId(participantId);
    }

    @Override
    public List<JoinConference> queryUnConfirmConferenceByParticipantId(Integer participantId) {
        return joinConferenceDao.queryUnConfirmConferenceByParticipantId(participantId);
    }
    @Override
    public List<JoinConference> queryConferenceByConferenceId(Integer conferenceId) {
        return joinConferenceDao.queryConferenceByConferenceId(conferenceId);
    }
}
