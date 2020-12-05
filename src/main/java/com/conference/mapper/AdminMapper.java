package com.conference.mapper;

import com.conference.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuCenYu
 * @Date: 2020/12/3 19:58
 **/


//管理员操作数据库Dao接口
@Mapper
@Repository
public interface AdminMapper {

    /**
     * 5、查询出所有的管理员
     * 1、管理员登录 ---- 根据account 和 pass 查询数据库
     * 2、添加管理员(非注册，但可从另一管理员处授权账号)----存入id、name、pass、account
     * 3、管理员账号信息的修改
     * 4、删除管理员账户
     */
    public List<Admin> queryAdmins();

    public Admin queryAdminByAccountAndPass(@Param("adminAccount")String adminAccount , @Param("adminPass") String adminPass);

    public int addAdmin(Admin admin);

    public int updateAdmin(Admin admin);

    public int deleteAdmin(Integer adminId);
}
