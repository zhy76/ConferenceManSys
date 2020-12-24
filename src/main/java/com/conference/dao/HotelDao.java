package com.conference.dao;

import com.conference.entity.Fleet;
import com.conference.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface HotelDao {
//    查看所有酒店
    List<Hotel> findAllHotel();

//    通过id找酒店
    Hotel getHotelById(@Param("hotelId") int hotelId);
//    电话查询酒店
    Hotel getHotelByPhone(@Param("hotelPhone")String hotelPhone);
//    增加酒店
    int addHotel(@Param("hotelName")String hotelName, @Param("hotelLocation")String hotelLocation,
                  @Param("hotelPhone")String hotelPhone, @Param("hotelPass")String hotelPass,
                  @Param("hotelInfo")String hotelInfo);
//    删除酒店
    int deleteHotelById(@Param("hotelId") int hotelId);
//更新酒店信息
    int updateHotel(@Param("hotelName")String hotelName, @Param("hotelLocation")String hotelLocation,
                    @Param("hotelPhone")String hotelPhone, @Param("hotelPass")String hotelPass,
                    @Param("hotelInfo")String hotelInfo,@Param("hotelId")int hotelId);

    int updateHotelPhoto(@Param("hotelPhoto") String adminPhoto, @Param("hotelId") Integer hotelId);
}
