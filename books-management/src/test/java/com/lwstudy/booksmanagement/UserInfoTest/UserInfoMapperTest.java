package com.lwstudy.booksmanagement.UserInfoTest;

import com.lwstudy.booksmanagement.common.UserGender;
import com.lwstudy.booksmanagement.entity.UserInfo;
import com.lwstudy.booksmanagement.mapper.UserInfoMapper;
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
 * @Created: 2018/7/27 10:13
 * @Description 读者信息测试类
 */

public class UserInfoMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    private final Logger logger = LoggerFactory.getLogger(UserInfoMapperTest.class);

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_insertUserInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo = new UserInfo();

        userInfo.setUserId("15050418101");
        userInfo.setUserName("张三");
        userInfo.setUserGender(UserGender.MALE);
        userInfo.setTelephone("15858585858");
        userInfo.setInstitute("经济管理学院");
        userInfo.setUserClass("15050418");
        userInfo.setBookCardId("00000001");

        logger.info("Insert Before : {}", userInfo);
        int effect = userInfoMapper.insertUserInfo(userInfo);
        logger.info("Insert Result =  {}", effect);
        logger.info("Insert After : {}", userInfo);
        sqlSession.close();
    }

    @Test
    public void test_deleteUserInfoById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        int effect = userInfoMapper.deleteUserInfoById("15050418104");
        logger.info("delete id = 10550418101 result = {} ", effect);
        sqlSession.close();
    }

    @Test
    public void test_updateUserInfoByObject() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo = new UserInfo();

        userInfo.setUserId("15050418101");
        userInfo.setUserName("李四");
        userInfo.setUserGender(UserGender.FEMALE);
        userInfo.setTelephone("13846578968");
        userInfo.setInstitute("计算机学院");
        userInfo.setUserClass("15050317");
        userInfo.setBookCardId("000000002");

        int effect = userInfoMapper.updateUserInfoByObject(userInfo);
        logger.info("Update After: {}", effect);
        sqlSession.close();
    }

    @Test
    public void test_queryUserInfoAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> userInfoList = userInfoMapper.queryUserInfoAll();
        logger.info("Select result = {}", userInfoList);
        sqlSession.close();
    }

    @Test
    public void test_queryUserInfoById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo = userInfoMapper.queryUserInfoById("15050418101");
        logger.info("Select userId = 15050418101 result = {}", userInfo);
        sqlSession.close();
    }
}
