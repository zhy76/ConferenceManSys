package com.conference.dao;

import com.conference.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface HotelDao {
    List<Hotel> findAllHotel();
    Hotel getHotel(@Param("hotelId") int hotelId);
    int addHotel(@Param("hotelName")String hotelName, @Param("hotelLocation")String hotelLocation,
                  @Param("hotelPhone")String hotelPhone, @Param("hotelPass")String hotelPass,
                  @Param("hotelInfo")String hotelInfo);
    int deleteHotelById(@Param("hotelId") int hotelId);

    int updateHotel(@Param("hotelName")String hotelName, @Param("hotelLocation")String hotelLocation,
                    @Param("hotelPhone")String hotelPhone, @Param("hotelPass")String hotelPass,
                    @Param("hotelInfo")String hotelInfo,@Param("hotelId")int hotelId);
}
