package com.conference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/3 18:32
 * @sno 6109118015
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

    private Integer participantId;
    private String participantName; //姓名
    private String participantJob; //参加者职业：例如教授、学生等
    private String participantWorkUnit; //参加者工作单位： xx学校、研究院等等
    private String participantEmail;
    private String participantPass;
    private String participantPhone;
    private String participantSex;
    private String participantIdCard; //参加者身份证
}