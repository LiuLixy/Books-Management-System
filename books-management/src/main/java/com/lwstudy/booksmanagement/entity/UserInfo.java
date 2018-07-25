package com.lwstudy.booksmanagement.entity;

import com.lwstudy.booksmanagement.common.UserGender;
import lombok.Data;

/**
 * @Author: LiuWang
 * @Created: 2018/7/25 10:05
 * @Description 读者信息
 */

@Data
public class UserInfo {
    private String userId;
    private String userName;
    private UserGender userGender;
    private String telephone;
    /**
     * 读者所在院系
     */
    private String institute;
    private String userClass;
    private String bookCardId;
}
