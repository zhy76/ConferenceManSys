package com.conference.service;

import com.conference.dao.LiveRoomDao;
import org.springframework.stereotype.Service;

@Service("LiveRoomService")
public class LiveRoomService {
    LiveRoomDao liveRoomDao;
}
