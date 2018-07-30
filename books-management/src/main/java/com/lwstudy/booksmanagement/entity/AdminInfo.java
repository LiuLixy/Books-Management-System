package com.lwstudy.booksmanagement.entity;

import lombok.Data;

/**
 * @Author: LiuWang
 * @Created: 2018/7/30 19:30
 */
@Data
public class AdminInfo {
    /**
     * 管理员编号
     */
    private int id;
    /**
     * 管理员姓名
     */
    private String name;
    /**
     * 管理员密码
     */
    private String password;
}
