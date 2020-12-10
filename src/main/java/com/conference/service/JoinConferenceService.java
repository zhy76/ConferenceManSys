package com.conference.service;

import com.conference.entity.JoinConference;

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


}
