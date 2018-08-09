package com.lwstudy.booksmanagement.mapper;

import com.lwstudy.booksmanagement.entity.AdminInfo;

import java.util.List;

/**
 * @Author: LiuWang
 * @Created: 2018/7/30 19:32
 */
public interface AdminInfoMapper {

    /**
     * 添加管理员
     * @param adminInfo
     * @return
     */
    int insertAdmin(AdminInfo adminInfo);

    /**
     * 删除管理员信息
     * @param id
     * @return
     */
    int deleteAdminById(int id);

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    int updateAdmin(int id, String password);

    /**
     * 通过输入的信息登录系统
     * @param adminInfo
     * @return
     */
    int queryAdminByObject(AdminInfo adminInfo);

    /**
     * 查询所有管理员信息
     * @return
     */
    List<AdminInfo> queryAllAdmin();
}
