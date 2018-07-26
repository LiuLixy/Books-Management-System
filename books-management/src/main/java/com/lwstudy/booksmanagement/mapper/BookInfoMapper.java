package com.lwstudy.booksmanagement.mapper;

import com.lwstudy.booksmanagement.entity.BookInfo;

import java.util.List;

/**
 * @Author: LiuWang
 * @Created: 2018/7/26 10:08
 */

public interface BookInfoMapper {

    /**
     * 插入一条图书信息到表中
     * @param bookInfo
     * @return 0:插入失败; 1:插入成功
     */
    int insertBookInfo(BookInfo bookInfo);
    /**
     * 通过 id 删除一条图书信息
     * @param bookId
     * @return 0:删除失败; 1:删除成功
     */
    int deleteBookInfoById(String bookId);
    /**
     * 更新图书信息
     * @param bookInfo
     * @return 0:更新失败; 1:更新成功
     */
    int updateBookInfoByObject(BookInfo bookInfo);
    /**
     * 查找所有的图书信息
     * @return 所有的图书信息，如果没有则返回null
     */
    List<BookInfo> queryBookInfoAll();
    /**
     * 通过图书 id 查找图书信息
     * @param bookId
     * @return 图书信息，如果不存在返回null
     */
    BookInfo queryBookInfoById(String bookId);
    /**
     * 通过图书名称查询图书信息
     * @param bookName
     * @return 图书信息，如果不存在返回null
     */
    BookInfo queryBookInfoByName(String bookName);
}
