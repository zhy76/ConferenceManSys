package com.conference.dao;

import com.conference.entity.Participant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 22:04
 **/
@Mapper
@Repository
public interface ParticipantDao {
    /**
     * 1、查询展示出所有注册的参会人员
     * 2、查询出指定姓名的参会人信息
     * 3、修改指定的参会人员的账号信息
     * 4、删除指定ID的参会人
     */
    public List<Participant> queryParticipants();

    public Participant queryParticipantByParticipantName(String participantName);

    public int updateParticipant(Participant participant);

    public int deleteParticipant(Integer participantId);
}
