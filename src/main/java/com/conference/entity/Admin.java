package com.conference.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer adminId; //管理员ID
    private String adminName; //管理员姓名
    private String adminPass; //管理员密码
    private String adminAccount; //管理员账号
}
