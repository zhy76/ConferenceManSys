package com.conference.dao;

import com.conference.entity.Hotel;
import com.conference.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoomDao {
    //    查看所有房间
    List<Room> findAllRoom();
    //    通过id找酒店房间
    List<Room> getRoomByHotelId(@Param("hotelId") int hotelId);
    //查找酒店某个房间
    Room getRoomByRoomId(@Param("roomId")String roomId,@Param("hotelId") int hotelId);
    //    增加房间
    int addRoom(@Param("roomId")String roomId,@Param("hotelId")int hotelId,@Param("isLive")int isLive,
                @Param("roomType")String roomType);
    //    删除房间
    int deleteRoomById(@Param("hotelId") int hotelId,@Param("roomId")String roomId);
    //更新房间状态
    int updateRoom(@Param("isLive")int isLive,
                   @Param("hotelId")int hotelId,@Param("roomId")String roomId);
}
