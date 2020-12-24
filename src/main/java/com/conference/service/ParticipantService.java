package com.conference.service;

import com.conference.entity.Participant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/5 21:04
 * @sno 6109118015
 */
public interface ParticipantService {
    /**
     * @Description 查看所有参会者Participant
     * @return 返回一个Participant列表
     **/
    public List<Participant> queryParticipants();

    //分页查询
    public  List<Participant> selectAll(Integer pageNum, Integer pageSize);

    /**
     * @Description 通过用户名查询Participant
     * @return 返回一个Participant
     **/
    public Participant queryParticipantByParticipantName(String participantName);

    /**
     * @Description 修改参会者Participant个人信息
     * @return 返回受影响行数
     **/
    public int updateParticipant(Participant participant);

    /**
     * @Description 通过id删除参会者Participant
     * @return 返回一个Participant列表
     **/
    public int deleteParticipant(Integer participantId);

    /**
     * @Description 通过id查询参会者Participant
     * @return 返回一个Participant列表
     **/
    public Participant queryParticipantByParticipantId(Integer participantId);


    /**
     * @Description 增加一个参会者(注册)
     * @return 返回受影响行数
     **/
    public int addAParticipant(Participant participant);

    /**
     * @Description 通过电话号码查询Participant
     * @return 返回一个Participant
     **/
    public Participant queryParticipantByParticipantPhone(String participantPhone);


}
