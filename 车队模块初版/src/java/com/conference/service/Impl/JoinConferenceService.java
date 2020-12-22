package com.conference.service;

import com.conference.entity.JoinConference;

import java.util.List;

/**
 * @Description
 * @Author л ��
 * @Date 2020/12/6 22:49
 * @sno 6109118015
 */

public interface JoinConferenceService {

    /**
     * @Description �μӻ���
     * @return ��Ӱ������
     **/
    public int joinAConference(JoinConference joinConference);

    /**
     * @Description �˳�����(��Ҫ�λ���id�ͻ���id)
     * @return ��Ӱ������
     **/
    public int cancelAJoinedConferenceById(Integer participantId,Integer conferenceId);

    /*
     * @Description ͨ���λ���id��ѯ���μӵ����л���
     * @return  JoinConference ���б�
     **/
    public List<JoinConference> queryConferenceByParticipantId(Integer participantId);

    List<JoinConference> queryJoinConferenceByConferenceId(Integer conferenceId);
}