package com.conference.dao;

import com.conference.entity.Participant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * 6、通过参会者电话查找参会者
     * 7、更新参会者头像
     */
    public List<Participant> queryParticipants();

    public Participant queryParticipantByParticipantName(String participantName);

    public List<Participant> fuzzyQueryParticipantByParticipantName(@Param("participantName") String participantName);

    public List<Participant> fuzzyQueryParticipantByParticipantPhone(@Param("participantPhone") String participantPhone);

    public int updateParticipant(Participant participant);

    public int deleteParticipant(Integer participantId);

    public Participant queryParticipantByParticipantId(Integer participantId);

    public int addAParticipant(Participant participant);

    public Participant queryParticipantByParticipantPhone(String participantPhone);

    public int updateParticipantPhoto(@Param("participantPhoto") String participantPhoto, @Param("participantId") Integer participantId);


}
