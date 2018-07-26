package com.lwstudy.booksmanagement.BookInfoTest;

import com.lwstudy.booksmanagement.common.BookType;
import com.lwstudy.booksmanagement.entity.BookInfo;
import com.lwstudy.booksmanagement.mapper.BookInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @Author: LiuWang
 * @Created: 2018/7/26 11:38
 * @Description 图书信息测试类
 */


public class BookInfoMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    private final Logger logger = LoggerFactory.getLogger(BookInfoMapperTest.class);

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_insertBookInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        BookInfo bookInfo = new BookInfo();

        bookInfo.setBookId("0000001");
        bookInfo.setBookName("Java从入门到放弃");
        bookInfo.setBookType(BookType.ComputerScience);
        bookInfo.setAuthor("LiuWang");
        bookInfo.setPublishingHouse("西安工业大学出版社");
        bookInfo.setPrice(99.99);

        logger.info("Insert Before : {}", bookInfo);
        int effect = bookInfoMapper.insertBookInfo(bookInfo);
        logger.info("Insert Result =  {}", effect);
        logger.info("Insert After : {}", bookInfo);
        sqlSession.close();
    }

    @Test
    public void test_deleteBookInfoById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        int effect = bookInfoMapper.deleteBookInfoById("0000001");
        logger.info("delete bookId = 000001 result = {} ", effect);
        sqlSession.close();
    }

    @Test
    public void test_updateBookInfoByObject() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId("0000001");
        bookInfo.setBookName("JavaScript从入门到放弃");
        bookInfo.setPrice(88.88);
        bookInfo.setAuthor("ZhangSan");
        bookInfo.setBookType(BookType.Language);
        bookInfo.setPublishingHouse("kfnsslmv");
        int effect = bookInfoMapper.updateBookInfoByObject(bookInfo);
        logger.info("Update bookId = 000001 result = {} ", effect);
        sqlSession.close();
    }

    @Test
    public void test_queryBookInfoAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        List<BookInfo> bookInfoList = bookInfoMapper.queryBookInfoAll();
        logger.info("Select result = {}", bookInfoList);
        sqlSession.close();
    }

    @Test
    public void test_queryBookInfoById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        BookInfo bookInfo = bookInfoMapper.queryBookInfoById("0000001");
        logger.info("Select bookId = 0000001 result = {}", bookInfo);
        sqlSession.close();
    }

    @Test
    public void test_queryBookInfoByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        BookInfo bookInfo = bookInfoMapper.queryBookInfoByName("Java从入门到放弃");
        logger.info("Select result: {}", bookInfo);
        sqlSession.close();
    }
}
