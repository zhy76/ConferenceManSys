package com.conference.service.impl;

import com.conference.dao.OrganizerDao;
import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/6 10:44
 **/
@Service("organizerService")
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerDao organizerDao;

    @Override
    public List<Organizer> queryOrganizers() {
        return organizerDao.queryOrganizers();
    }

    @Override
    public Organizer queryOrganizerByOrganizerUnit(String organizerUnit) {
        return organizerDao.queryOrganizerByOrganizerUnit(organizerUnit);
    }

    @Override
    public int updateOrganizer(Organizer organizer) {
        return organizerDao.updateOrganizer(organizer);
    }

    @Override
    public int deleteOrganizer(Integer organizerId) {
        return organizerDao.deleteOrganizer(organizerId);
    }

    @Override
    public Organizer queryOrganizerByOrganizerId(Integer organizerId) {
        return organizerDao.queryOrganizerByOrganizerId(organizerId);
    }
}
