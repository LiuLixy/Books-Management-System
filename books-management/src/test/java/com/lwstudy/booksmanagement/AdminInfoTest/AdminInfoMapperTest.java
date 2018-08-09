package com.lwstudy.booksmanagement.AdminInfoTest;

import com.lwstudy.booksmanagement.entity.AdminInfo;
import com.lwstudy.booksmanagement.mapper.AdminInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: LiuWang
 * @Created: 2018/8/2 19:09
 */
public class AdminInfoMapperTest {

    private static SqlSessionFactory sqlSessionFactory;
    private final Logger logger = LoggerFactory.getLogger(AdminInfoMapperTest.class);

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_insertAdmin() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setId(1002);
        adminInfo.setName("zhangsan");
        adminInfo.setPassword("123456");

        int effect = adminInfoMapper.insertAdmin(adminInfo);
        logger.info("Insert Result = {}", effect);
    }
}
