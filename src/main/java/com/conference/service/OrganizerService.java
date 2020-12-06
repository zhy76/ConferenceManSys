package com.conference.service;

import com.conference.entity.Organizer;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/6 10:44
 **/
public interface OrganizerService {

    public List<Organizer> queryOrganizers();

    public Organizer queryOrganizerByOrganizerUnit(String organizerUnit);

    public int updateOrganizer(Organizer organizer);

    public int deleteOrganizer(Integer organizerId);

    public Organizer queryOrganizerByOrganizerId(Integer organizerId);

}
