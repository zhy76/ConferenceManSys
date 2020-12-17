package com.conference.service.impl;

import com.conference.dao.AdminDao;
import com.conference.entity.Admin;
import com.conference.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/5 13:09
 **/
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> queryAdmins() {
        return adminDao.queryAdmins();
    }

    @Override
    public Admin login(String adminAccount, String adminPass) {
        return adminDao.queryAdminByAccountAndPass(adminAccount,adminPass);
    }

    @Override
    public void registAdmin(Admin admin) {

    }

    @Override
    public void addAdmin(Admin admin) {
        adminDao.addAdmin(admin);
    }

    @Override
    public void deleteAdminById(Integer adminId) {
        adminDao.deleteAdmin(adminId);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminDao.updateAdmin(admin);
    }

    @Override
    public boolean existAdminAccount(String adminAccount) {
        return false;
    }
}
