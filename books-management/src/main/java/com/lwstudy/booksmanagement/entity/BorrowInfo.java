package com.lwstudy.booksmanagement.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: LiuWang
 * @Created: 2018/7/25 10:11
 * @Description 图书借阅信息
 */

@Data
public class BorrowInfo {
    private String bookCardId;
    private String userId;
    private String userName;
    private String bookId;
    /**
     * 借书日期
     */
    private LocalDate borrowTime;
    /**
     * 还书期限
     */

    private LocalDate deadlineTime;
    /**
     *   是否续借
     */
    private boolean renew;
}
