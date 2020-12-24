package com.conference.service.impl;

import com.conference.dao.HotelDao;
import com.conference.entity.Hotel;
import com.conference.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("HotelService")
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelDao hotelDao;

    @Override
    public List<Hotel> findAllHotel() {
        return hotelDao.findAllHotel();
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        return hotelDao.getHotelById(hotelId);
    }

    @Override
    public Hotel getHotelByPhone(String hotelPhone) {
        return hotelDao.getHotelByPhone(hotelPhone);
    }

    @Override
    public int addHotel(Hotel hotel) {
        return hotelDao.addHotel(hotel.getHotelName(),hotel.getHotelLocation(),hotel.getHotelPhone(),hotel.getHotelPass(),hotel.getHotelInfo());
    }

    @Override
    public int deleteHotelById(int hotelId) {
        return hotelDao.deleteHotelById(hotelId);
    }

    @Override
    public int updateHotel(Hotel hotel) {
        return hotelDao.updateHotel(hotel.getHotelName(),hotel.getHotelLocation(),hotel.getHotelPhone(),hotel.getHotelPass(),hotel.getHotelInfo(),hotel.getHotelId());
    }
}
