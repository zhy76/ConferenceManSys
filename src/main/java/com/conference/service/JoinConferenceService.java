package com.conference.service;

import com.conference.entity.JoinConference;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 22:49
 * @sno 6109118015
 */

public interface JoinConferenceService {

    /**
     * @Description 参加会议
     * @return 受影响行数
     **/
    public int joinAConference(JoinConference joinConference);

    /**
     * @Description 退出会议(需要参会者id和会议id)
     * @return 受影响行数
     **/
    public int cancelAJoinedConferenceById(Integer participantId,Integer conferenceId);

    /*
     * @Description 通过参会者id查询他参加的所有会议
     * @return  JoinConference 的列表
     **/
    public List<JoinConference> queryConferenceByParticipantId(Integer participantId);


    /*
     * @Description 通过参会者id查询他未审核的所有会议
     * @return  JoinConference 的列表
     **/
    public List<JoinConference> queryUnConfirmConferenceByParticipantId(Integer participantId);
    /*
     * @Description 通过会议id查询所有会议
     * @return  JoinConference 的列表
     **/
    public List<JoinConference> queryConferenceByConferenceId(Integer conferenceId);
    List<JoinConference> queryJoinConferenceByConferenceId(Integer conferenceId);

    JoinConference queryJoinedConferenceByParticipantIdAndConferenceId(Integer participantId,
                                                                       Integer conferenceId);

    int addAJoinedConferenceToRoom(Integer participantId, Integer hotelId, Integer conferenceId);

    int confirmAJoinedConferenceById(Integer participantId, Integer conferenceId);
}
