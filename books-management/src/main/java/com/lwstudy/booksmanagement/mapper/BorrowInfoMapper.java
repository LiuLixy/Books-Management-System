package com.lwstudy.booksmanagement.mapper;

import com.lwstudy.booksmanagement.entity.BookInfo;
import com.lwstudy.booksmanagement.entity.BorrowInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: LiuWang
 * @Created: 2018/7/24 11:04
 */

public interface BorrowInfoMapper {
    /**
     * 插入一条借阅信息到表中
     * @param borrowInfo
     * @return 0:插入失败; 1:插入成功
     */
    int insertBorrowInfo(BorrowInfo borrowInfo);
    /**
     * // 更新借阅信息表
     * @param borrowInfo
     * @return 0:更新失败; 1:更新成功
     */
    int updateBorrowInfoByObject(BorrowInfo borrowInfo);
    /**
     * 通过图书 id 删除一条借阅信息
     * @param bookId
     * @return 0:删除失败; 1:删除成功
     */
    int deleteBorrowInfoByBookId(String bookId);
    /**
     * 查询所有的图书借阅信息
     * @return 所有的图书信息，如果没有则返回null
     */
    List<BorrowInfo> queryBorrowInfoAll();
    /**
     * 通过图书 id 查询图书借阅信息
     * @param bookName
     * @return 图书借阅信息，如果不存在返回null
     */
    BorrowInfo queryBorrowInfoByBookId(String bookName);
    /**
     * 通过读者 id 查询图书借阅信息
     * @param userId
     * @return 该读者的所有借阅信息，如果不存在返回null
     */
    List<BorrowInfo> queryBorrowInfoByUserId(String userId);
}
