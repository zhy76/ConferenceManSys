package com.conference.service;

import com.conference.dao.LiveRoomDao;
import com.conference.entity.LiveRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LiveRoomService")
public interface LiveRoomService {
    List<LiveRoom> findAllLiveRoom();
    List<LiveRoom> findAllLiveRoomByConferenceId(int conferenceId);
    List<LiveRoom> findAllLiveRoomByParticipantId(int participantId);
    int addLiveRoom(LiveRoom liveRoom);
    int deleteLiveRoom(int participantId, int conferenceId);
    int updateLiveRoomByParticipantId(LiveRoom liveRoom);
}
