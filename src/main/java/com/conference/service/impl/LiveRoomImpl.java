package com.conference.service.impl;

import com.conference.dao.LiveRoomDao;
import com.conference.entity.LiveRoom;
import com.conference.service.LiveRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LiveRoomService")
public class LiveRoomImpl implements LiveRoomService {
    @Autowired
    LiveRoomDao liveRoomDao;

    @Override
    public List<LiveRoom> findAllLiveRoom() {
        return liveRoomDao.findAllLiveRoom();
    }

    @Override
    public List<LiveRoom> findAllLiveRoomByConferenceId(int conferenceId) {
        return liveRoomDao.findAllLiveRoomByConferenceId(conferenceId);
    }

    @Override
    public List<LiveRoom> findAllLiveRoomByParticipantId(int participantId) {
        return liveRoomDao.findAllLiveRoomByParticipantId(participantId);
    }

    @Override
    public List<LiveRoom> findAllLiveRoomByHotelId(int hotelId) {
        return liveRoomDao.findAllLiveRoomByHotelId(hotelId);
    }

    @Override
    public List<LiveRoom> findAllLiveRoomByAll(int participantId, int conferenceId, int hotelId) {
        return liveRoomDao.findAllLiveRoomByAll(participantId,conferenceId,hotelId);
    }

    @Override
    public int addLiveRoom(LiveRoom liveRoom) {
        return liveRoomDao.addLiveRoom(liveRoom.getParticipantId(),liveRoom.getHotelId(),liveRoom.getConferenceId(),liveRoom.getRoomId());
    }

    @Override
    public int deleteLiveRoom(int participantId, int conferenceId) {
        return liveRoomDao.deleteLiveRoom(participantId,conferenceId);
    }

    @Override
    public int updateLiveRoom(LiveRoom liveRoom) {
        return liveRoomDao.updateLiveRoom(liveRoom.getRoomId(),liveRoom.getParticipantId(),liveRoom.getConferenceId());
    }

    @Override
    public int deleteLiveRoomByAll(int participantId, int conferenceId, int hotelId) {
        return liveRoomDao.deleteLiveRoomByAll(participantId,conferenceId,hotelId);
    }
}
