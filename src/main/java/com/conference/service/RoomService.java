package com.conference.service;

import com.conference.entity.Room;

import java.util.List;

public interface RoomService {
    //    查看所有房间
    List<Room> findAllRoom();
    //    通过id找酒店房间
    List<Room> getRoomByHotelId(int hotelId);

    //    增加房间
    int addRoom(Room room);
    //    删除房间
    int deleteRoomById(int hotelId,String roomId);
    //更新房间状态
    int updateRoom(Room room);
}
