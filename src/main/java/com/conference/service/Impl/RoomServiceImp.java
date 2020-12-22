package com.conference.service.Impl;

import com.conference.dao.RoomDao;
import com.conference.entity.Room;
import com.conference.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoomService")
public class RoomServiceImp implements RoomService {
    @Autowired
    RoomDao roomDao;

    @Override
    public List<Room> findAllRoom() {
        return roomDao.findAllRoom();
    }

    @Override
    public List<Room> getRoomByHotelId(int hotelId) {
        return roomDao.getRoomByHotelId(hotelId);
    }

    @Override
    public int addRoom(Room room) {
        return roomDao.addRoom(room.getRoomId(),room.getHotelId(),room.getIsLive(),room.getRoomType());
    }

    @Override
    public int deleteRoomById(int hotelId, String roomId) {
        return roomDao.deleteRoomById(hotelId, roomId);
    }

    @Override
    public int updateRoom(Room room) {
        return roomDao.updateRoom(room.getIsLive(),room.getRoomType(),room.getHotelId(),room.getRoomId());
    }
}
