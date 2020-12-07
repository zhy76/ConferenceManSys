package com.conference.service.imp;

import com.conference.dao.LiveRoomDao;
import com.conference.entity.LiveRoom;
import com.conference.service.LiveRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LiveRoomService")
public class LiveRoomImp implements LiveRoomService {
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
    public int addLiveRoom(LiveRoom liveRoom) {
        return liveRoomDao.addLiveRoom(liveRoom.getParticipantId(),liveRoom.getHotelId(),liveRoom.getConferenceId(),liveRoom.getRoomId());
    }

    @Override
    public int deleteLiveRoom(int participantId, int conferenceId) {
        return liveRoomDao.deleteLiveRoom(participantId,conferenceId);
    }

    @Override
    public int updateLiveRoomByParticipantId(LiveRoom liveRoom) {
        return liveRoomDao.updateLiveRoomByParticipantId(liveRoom.getRoomId(),liveRoom.getParticipantId());
    }
}
