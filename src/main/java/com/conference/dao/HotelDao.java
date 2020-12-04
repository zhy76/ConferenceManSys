package com.conference.dao;

import com.conference.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface HotelDao {
    List<Hotel> findAllHotel();
}
