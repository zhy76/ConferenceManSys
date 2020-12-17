package com.conference.service;

import com.conference.entity.Organizer;

import java.util.List;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:39
 * @stuid 6109118041
 */
public interface OrganizerService {
    List<Organizer> findAllOrganizer();

    Organizer findOrganizerById(Integer organizerId);

    int updateOrganizer(Organizer organizer);

    Organizer findOrganizerByPhone(String organizerPhone);

    int addOrganizer(Organizer organizer);

    int deleteOrganizerById(Integer organizerId);
}
