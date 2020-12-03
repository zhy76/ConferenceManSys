package com.conference.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {
    private Integer organizerId; //组织单位ID
    private String organizerPass; //组织单位密码
    private String organizerPhone; //组织单位电话
    private String organizerUnit; //组织单位
    private String organizerEmail; //组织单位邮箱

}
