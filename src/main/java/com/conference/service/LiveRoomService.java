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
    List<LiveRoom> findAllLiveRoomByHotelId(int hotelId);
    List<LiveRoom> findAllLiveRoomByAll(int participantId, int conferenceId,int hotelId);
    int addLiveRoom(LiveRoom liveRoom);
    int deleteLiveRoom(int participantId, int conferenceId);
    int updateLiveRoom(LiveRoom liveRoom);
    int deleteLiveRoomByAll(int participantId, int conferenceId,int hotelId);
}
