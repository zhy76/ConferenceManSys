package com.conference.service;

import com.conference.dao.HotelDao;
import com.conference.entity.Hotel;
import org.springframework.stereotype.Service;

@Service("HotelService")
public class HotelService {
    HotelDao hotelDao;
}
