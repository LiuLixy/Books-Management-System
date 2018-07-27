package com.lwstudy.booksmanagement.BorrowInfoTest;

import com.lwstudy.booksmanagement.entity.BorrowInfo;
import com.lwstudy.booksmanagement.mapper.BorrowInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @Author: LiuWang
 * @Created: 2018/7/27 13:24
 * @Description 图书借阅信息测试类
 */


public class BorrowInfoMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    private final Logger logger = LoggerFactory.getLogger(BorrowInfoMapperTest.class);

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_insertBorrowInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);

        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBookCardId("123456789");
        borrowInfo.setBookId("0000001");
        borrowInfo.setUserId("0000001");
        borrowInfo.setUserName("张三");
        borrowInfo.setBorrowTime(LocalDate.now());
        LocalDate borrowTime = LocalDate.now();
        // 还书期限为30天
        LocalDate deadlineTime = borrowTime.plus(30, ChronoUnit.DAYS);
        borrowInfo.setDeadlineTime(deadlineTime);
        borrowInfo.setRenew(false);

        int effect = borrowInfoMapper.insertBorrowInfo(borrowInfo);
        logger.info("Insert Result =  {}", effect);
        sqlSession.close();
    }

    @Test
    public void test_deleteBorrowInfoByBookId() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);

        int effect = borrowInfoMapper.deleteBorrowInfoByBookId("0000001");
        logger.info("delete bookId = 000001 result = {} ", effect);
        sqlSession.close();
    }

    @Test
    public void test_updateBorrowInfoByObject() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBookId("0000001");
        borrowInfo.setBookCardId("123456987");
        borrowInfo.setUserId("0000002");
        borrowInfo.setUserName("李四");
        borrowInfo.setBorrowTime(LocalDate.now());
        LocalDate borrowTime = LocalDate.now();
        // 还书期限为30天
        LocalDate deadlineTime = borrowTime.plus(30, ChronoUnit.DAYS);
        borrowInfo.setDeadlineTime(deadlineTime);
        borrowInfo.setRenew(true);

        int effect = borrowInfoMapper.updateBorrowInfoByObject(borrowInfo);
        logger.info("Update After : {}", borrowInfo);
        sqlSession.close();
    }

    @Test
    public void test_queryBorrowInfoAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
        List<BorrowInfo> borrowInfoList = borrowInfoMapper.queryBorrowInfoAll();
        logger.info("Select result = {}", borrowInfoList);
        sqlSession.close();
    }

    @Test
    public void test_queryBorrowInfoByBookId() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
        BorrowInfo borrowInfo = borrowInfoMapper.queryBorrowInfoByBookId("0000001");
        logger.info("Select bookId = 0000001 result = {}", borrowInfo);
        sqlSession.close();
    }

    @Test
    public void test_queryBorrowInfoByUserId() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
        List<BorrowInfo> borrowInfoList = borrowInfoMapper.queryBorrowInfoByUserId("0000001");
        logger.info("Select result = {}", borrowInfoList);
        sqlSession.close();

    }
}
