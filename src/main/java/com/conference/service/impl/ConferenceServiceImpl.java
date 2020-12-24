package com.conference.service.impl;

import com.conference.entity.Conference;
import com.conference.dao.ConferenceDao;
import com.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 23:30
 **/
@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService {

    @Override
    public int addConference(Conference conference) {
        return conferenceDao.addConference(conference);
    }

    @Override
    public Conference queryConferenceById(Integer conferenceId) {
        return conferenceDao.queryConferenceById(conferenceId);
    }

    @Override
    public List<Conference> queryConferenceByFleetId(Integer fleetId) {
        return conferenceDao.queryConferenceByFleetId(fleetId);
    }

    @Autowired
    private ConferenceDao conferenceDao;

    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private FleetService fleetService;

    @Autowired
    private HotelService hotelService;


    @Override
    public List<Conference> queryConferences() {
        return conferenceDao.queryConferences();
    }

    //分页查询
    public List<JSONObject> selectAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize); //第1页个数为10
        List<Conference> conferencesList = queryConferences();
        List <JSONObject> trueConferencesList = new LinkedList<>();
        for( Conference conference :  conferencesList)
        { //遍历每个会议，查询出各个ID对应的name属性
            Organizer organizer = organizerService.findOrganizerById(conference.getOrganizerId());
            Fleet fleet = fleetService.findFleetById(conference.getFleetId());
            Hotel hotel = hotelService.getHotelById(conference.getHotelId());
            JSONObject object = new JSONObject();
            object.put("conferenceId",conference.getConferenceId());
            object.put("conferenceName",conference.getConferenceName());

            object.put("organizerId",conference.getOrganizerId());
            object.put("organizerUnit",organizer.getOrganizerUnit());

            object.put("conferenceStart",conference.getConferenceStart());
            object.put("conferenceEnd",conference.getConferenceEnd());
            object.put("conferenceLocation",conference.getConferenceLocation());

            object.put("hotelId",conference.getHotelId());
            object.put("hotelName",hotel.getHotelName());

            object.put("fleetId",conference.getFleetId());
            object.put("fleetName",fleet.getFleetName());

            object.put("conferenceInfo",conference.getConferenceInfo());
            trueConferencesList.add(object);
        }
        PageInfo<JSONObject> pi  = new PageInfo<>(trueConferencesList);
        return pi.getList();
    }

    @Override
    public int updateConference(Conference conference) {
        return conferenceDao.updateConference(conference);
    }

    @Override
    public int deleteConference(Integer conferenceId) {
        return conferenceDao.deleteConference(conferenceId);
    }

    @Override
    public Conference queryConferenceByConferenceId(Integer conferenceId) {
        return conferenceDao.queryConferenceByConferenceId(conferenceId);
    }

    @Override
    public int addConference(Conference conference) {
        return 0;
    }

    @Override
    public List<Conference> queryConferenceByOrganizerId(Integer organizerId) { //用于修改
        return conferenceDao.queryConferenceByOrganizerId(organizerId);
    }
}
