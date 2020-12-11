package com.conference.service.impl;

import com.conference.dao.OrganizerDao;
import com.conference.entity.Organizer;
import com.conference.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 左海余
 * @description
 * @date 2020/12/11 11:40
 * @stuid 6109118041
 */
public class OrganizerServiceImpl implements OrganizerService {
    @Autowired
    private OrganizerDao organizerDao;
    @Override
    public int updateOrganizer(Organizer organizer) {
        return organizerDao.updateOrganizer(organizer);
    }
}
