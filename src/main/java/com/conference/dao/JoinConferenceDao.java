package com.conference.dao;

import com.conference.entity.JoinConference;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
     *
     * @
     **/

    public int joinAConference(JoinConference joinConference);

    public int cancelAJoinedConferenceById(Integer participantId,Integer conferenceId);


}
