package com.conference.service.impl;

import com.conference.dao.DriverDao;
import com.conference.dao.OrganizerDao;
import com.conference.entity.Driver;
import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:40
 * @stuid 6109118041
 */
@Service("OrganizerService")
public class OrganizerServiceImpl implements OrganizerService {
    @Autowired
    private OrganizerDao organizerDao;

    @Override
    public List<Organizer> findAllOrganizer() {
        return organizerDao.findAllOrganizer();
    }

    @Override
    public Organizer findOrganizerById(Integer organizerId) {
        return organizerDao.findOrganizerById(organizerId);
    }

    @Override
    public Organizer findOrganizerByPhone(String organizerPhone) {
        return organizerDao.findOrganizerByPhone(organizerPhone);
    }

    @Override
    public int updateOrganizer(Organizer organizer) {
        return organizerDao.updateOrganizer(organizer.getOrganizerId(),organizer.getOrganizerEmail(),
                organizer.getOrganizerUnit(),organizer.getOrganizerPass(),organizer.getOrganizerPhone());
    }

    @Override
    public int addOrganizer(Organizer organizer) {
        return organizerDao.addOrganizer(organizer.getOrganizerId(),organizer.getOrganizerEmail(),organizer.getOrganizerUnit(),
                organizer.getOrganizerPass(),organizer.getOrganizerPhone());
    }

    @Override
    public int deleteOrganizerById(Integer organizerId) {
        return organizerDao.deleteDriverById(organizerId);
    }

}
