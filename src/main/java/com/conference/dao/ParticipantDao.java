package com.conference.dao;

import com.conference.entity.Participant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 17:27
 * @sno 6109118015
 */
@Mapper
@Repository
public interface ParticipantDao {
    /**
     * 1、查询展示出所有注册的参会人员
     * 2、查询出指定姓名的参会人信息
     * 3、修改指定的参会人员的账号信息
     * 4、删除指定ID的参会人
     * 5、查询出指定ID的参会人
     */
    public List<Participant> queryParticipants();

    public Participant queryParticipantByParticipantName(String participantName);

    public int updateParticipant(Participant participant);

    public int deleteParticipant(Integer participantId);

    Participant queryParticipantByParticipantId(Integer participantId);

    /**
     * 1.无账号注册成功时，往数据库中participant表中增加一个参会者(addAParticipant)
     * 2.已有账号登录时，查询账号密码是否正确(queryParticipantByParticipantName)
     * 3.修改自己的个人信息(updateParticipant)
     *
     **/

     public int addAParticipant(Participant participant);

    public Participant queryParticipantByParticipantPhone(String participantPhone);




}
