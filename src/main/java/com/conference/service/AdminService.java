package com.conference.service;

import com.conference.entity.Admin;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/4 23:01
 **/
public interface AdminService {

    /**
     * 查询所有管理员账户
     * @return 管理员列表
     */
    public List<Admin> queryAdmins();

    /**
     * 登录管理员
     * @return 返回null，表示登录失败，返回有值，则表示登录成功
     */
    public Admin login(String adminAccount , String adminPass);

    /**
     * 注册管理员
     * @param admin
     */
    public void registAdmin(Admin admin);

    /**
     * 添加管理员账户
     * @param admin
     */
    public void addAdmin(Admin admin);

    /**
     * 根据id删除管理员账户
     * @param adminId
     */
    public void deleteAdminById(Integer adminId);


    /**
     * 修改管理员账户的基本信息
     * @param admin
     */
    public void updateAdmin(Admin admin);

    /**
     * 添加注册管理员账号时,判断是否已存在
     * @param adminAccount
     * @return
     */
    public boolean existAdminAccount(String adminAccount);
}
