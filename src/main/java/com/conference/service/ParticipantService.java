package com.conference.service;

import com.conference.entity.Participant;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/6 10:43
 **/
public interface ParticipantService {

    public List<Participant> queryParticipants();

    public Participant queryParticipantByParticipantName(String participantName);

    public int updateParticipant(Participant participant);

    public int deleteParticipant(Integer participantId);

    public Participant queryParticipantByParticipantId(Integer participantId);

}
