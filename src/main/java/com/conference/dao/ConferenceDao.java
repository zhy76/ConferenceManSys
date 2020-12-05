package com.conference.dao;

import com.conference.entity.Conference;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/3 22:12
 **/

@Mapper
@Repository
public interface ConferenceDao {
    /**
     * 1、展示所有会议---- 查询所有会议
     * 2、删除会议
     * 3、更改会议信息
     */

    public List<Conference> queryConferences();

    public int updateConference(Conference conference);

    public int deleteConference(Integer conferenceId);
}
