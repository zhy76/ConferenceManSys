package com.conference.service;

import com.conference.dao.HotelDao;
import com.conference.entity.Hotel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("HotelService")
public interface HotelService {
    List<Hotel> findAllHotel();
    Hotel getHotelById(int hotelId);
    Hotel getHotelByPhone(String hotelPhone);
    int addHotel(Hotel hotel);
    int deleteHotelById(int hotelId);
    int updateHotel(Hotel hotel);
}
