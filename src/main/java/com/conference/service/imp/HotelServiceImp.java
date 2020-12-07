package com.conference.service.imp;

import com.conference.dao.HotelDao;
import com.conference.entity.Hotel;
import com.conference.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("HotelService")
public class HotelServiceImp implements HotelService {
    @Autowired
    HotelDao hotelDao;

    @Override
    public List<Hotel> findAllHotel() {
        return hotelDao.findAllHotel();
    }

    @Override
    public Hotel getHotel(int hotelId) {
        return hotelDao.getHotel(hotelId);
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
