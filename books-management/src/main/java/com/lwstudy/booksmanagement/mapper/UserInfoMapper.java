package com.lwstudy.booksmanagement.mapper;

import com.lwstudy.booksmanagement.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: LiuWang
 * @Created: 2018/7/27 09:34
 */

public interface UserInfoMapper {
    /**
     * 插入一条读者信息到表中
     * @param userInfo
     * @return 0:插入失败; 1:插入成功
     */
    int insertUserInfo(UserInfo userInfo);
    /**
     * 通过读者 id 删除一条读者信息
     * @param userId
     * @return 0:删除失败; 1:删除成功
     */
    int deleteUserInfoById(String userId);
    /**
     * 更新读者信息
     * @param userInfo
     * @return 0:更新失败; 1:更新成功
     */
    int updateUserInfoByObject(UserInfo userInfo);
    /**
     * 查找所有的读者信息
     * @return 所有的读者信息，如果不存在返回null
     */
    List<UserInfo> queryUserInfoAll();
    /**
     * 通过读者 id 查询读者信息
     * @param userId
     * @return 该读者的信息，如果不存在返回null
     */
    UserInfo queryUserInfoById(String userId);
}
