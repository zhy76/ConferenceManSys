package com.conference.dao;

import com.conference.entity.JoinConference;
import com.conference.entity.Participant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 20:27
 * @sno 6109118015
 */
@Mapper
@Repository
public interface JoinConferenceDao {
    /**
     * 1、加入会议
     * 2、取消已参加的会议
     * 3、查询某人参加的所有会议
     * @
     **/

    public int joinAConference(JoinConference joinConference);

    public int cancelAJoinedConferenceById(Integer participantId,Integer conferenceId);
    public int confirmAJoinedConferenceById(Integer participantId,Integer conferenceId);
    public int addAJoinedConferenceToRoom(Integer participantId,Integer hotelId,Integer conferenceId);
    public List<JoinConference> queryConferenceByParticipantId(Integer participantId);

    public List<JoinConference> queryUnConfirmConferenceByParticipantId(Integer participantId);
    public List<JoinConference> queryConferenceByConferenceId(Integer conferenceId);
}

    /**
     * 给车队用的
     * @param participantId
     * @param conferenceId
     * @return
     */
    public JoinConference queryJoinedConferenceByParticipantIdAndConferenceId(Integer participantId,
                                                                              Integer conferenceId);

    public List<JoinConference> queryUnConfirmConferenceByParticipantId(Integer participantId);
}