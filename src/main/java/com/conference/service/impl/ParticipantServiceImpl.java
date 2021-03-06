package com.conference.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.ParticipantDao;
import com.conference.entity.Conference;
import com.conference.entity.Participant;
import com.conference.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/6 23:09
 * @sno 6109118015
 */

@Service("participantService")
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantDao participantDao;

    @Override
    public List<Participant> queryParticipants() {
        return participantDao.queryParticipants();
    }

    @Override
    public List<Participant> selectAll(Integer pageNum, Integer pageSize) {
        return null;
    }

//    //分页查询
//    @Override
//    public  List<Participant> selectAll(Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum,pageSize); //第1页个数为10
//        List<Participant> participantsList = participantDao.queryParticipants();
//        PageInfo<Participant> pi  = new PageInfo<>(participantsList);
//        return pi.getList();
//    }

    @Override
    public List<Participant> fuzzyQueryParticipantByParticipantName(String participantName){
        return participantDao.fuzzyQueryParticipantByParticipantName(participantName);

    }

    @Override
    public List<Participant> fuzzyQueryParticipantByParticipantPhone(String participantPhone){
        return participantDao.fuzzyQueryParticipantByParticipantPhone(participantPhone);
    }


    @Override
    public Participant queryParticipantByParticipantName(String participantName) {
        return participantDao.queryParticipantByParticipantName(participantName);
    }

    @Override
    public int updateParticipant(Participant participant) {
        return participantDao.updateParticipant(participant);
    }

    @Override
    public int deleteParticipant(Integer participantId) {
        return participantDao.deleteParticipant(participantId);
    }

    @Override
    public Participant queryParticipantByParticipantId(Integer participantId) {
        return participantDao.queryParticipantByParticipantId(participantId);
    }

    @Override
    public int addAParticipant(Participant participant) {
        return participantDao.addAParticipant(participant);
    }

    @Override
    public Participant queryParticipantByParticipantPhone(String participantPhone) {
        return participantDao.queryParticipantByParticipantPhone(participantPhone);
    }
}