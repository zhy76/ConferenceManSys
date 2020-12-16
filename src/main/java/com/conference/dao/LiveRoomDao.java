package com.conference.dao;

import com.conference.entity.Hotel;
import com.conference.entity.LiveRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface LiveRoomDao {
    List<LiveRoom> findAllLiveRoom();
    List<LiveRoom> findAllLiveRoomByConferenceId(@Param("conferenceId") int conferenceId);
    List<LiveRoom> findAllLiveRoomByParticipantId(@Param("participantId") int participantId);
    List<LiveRoom> findAllLiveRoomByHotelId(@Param("hotelId")int hotelId);
    List<LiveRoom> findAllLiveRoomByAll(@Param("participantId") int participantId,@Param("conferenceId") int conferenceId
            ,@Param("hotelId")int hotelId);
    int addLiveRoom(@Param("participantId")int participantId, @Param("hotelId")int hotelId,
                 @Param("conferenceId")int conferenceId, @Param("roomId")String roomId);
    int deleteLiveRoom(@Param("participantId") int participantId,@Param("conferenceId") int conferenceId);
    int updateLiveRoom(@Param("roomId")String roomId, @Param("participantId")int participantId,
                       @Param("hotelId")int hotelId,@Param("conferenceId") int conferenceId);
    int deleteLiveRoomByAll(@Param("participantId") int participantId,@Param("conferenceId") int conferenceId
            ,@Param("hotelId")int hotelId);
}
