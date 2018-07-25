package com.lwstudy.booksmanagement.entity;

import com.lwstudy.booksmanagement.common.BookType;
import lombok.Data;

/**
 * @Author: LiuWang
 * @Created: 2018/7/25 10:00
 * @Description 图书信息
 */

@Data
public class BookInfo {
    private String bookId;
    private String bookName;
    private BookType bookType;
    private String author;
    private String publishingHouse;
    private double price;

}
