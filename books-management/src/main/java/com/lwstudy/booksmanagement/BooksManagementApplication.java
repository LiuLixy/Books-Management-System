package com.lwstudy.booksmanagement;

import com.lwstudy.booksmanagement.common.BookType;
import com.lwstudy.booksmanagement.common.UserGender;
import com.lwstudy.booksmanagement.entity.BookInfo;
import com.lwstudy.booksmanagement.entity.BorrowInfo;
import com.lwstudy.booksmanagement.entity.UserInfo;
import com.lwstudy.booksmanagement.mapper.BookInfoMapper;
import com.lwstudy.booksmanagement.mapper.BorrowInfoMapper;
import com.lwstudy.booksmanagement.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: LiuWang
 * @Created: 2018/7/27 14:50
 */

class Menu {
    public Menu() {
        System.out.println("***********      欢迎使用该图书管理系统     ***********");
        System.out.println("*** 1.查询所有图书信息   2.根据图书编号查询图书信息 ***");
        System.out.println("*** 3.根据图书名称查询图书信息   4.增加图书信息条目 ***");
        System.out.println("*** 5.根据图书编号删除图书信息       6.更新图书信息 ***");
        System.out.println("*** 7.查询所有的读者信息 8.根据读者编号查询读者信息 ***");
        System.out.println("*** 9.插入读者信息条目  10.根据读者编号删除读者信息 ***");
        System.out.println("*** 11.更新读者信息       12.查询所有的图书借阅信息 ***");
        System.out.println("*** 13.通过图书编号查询借阅信息     14.更新借阅信息 ***");
        System.out.println("*** 15.通过读者编号查询借阅信息 16.插入借阅信息条目 ***");
        System.out.println("*** 17.根据图书编号删除借阅信息          0.退出系统 ***");
    }
}

class test {

    private static SqlSessionFactory sqlSessionFactory;
    private final Logger logger = LoggerFactory.getLogger(BooksManagementApplication.class);

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public test() {
        new Menu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请选择: ");
        while (scanner.hasNext()) {
            int choice = scanner.nextInt();
            switch (choice) {
                // 查询所有图书信息
                case 1:
                    SqlSession sqlSession = sqlSessionFactory.openSession(true);
                    BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
                    List<BookInfo> bookInfoList = bookInfoMapper.queryBookInfoAll();
                    logger.info("Select result = {}", bookInfoList);
                    sqlSession.close();
                    break;
                // 根据图书编号查询图书信息
                case 2:
                    sqlSession = sqlSessionFactory.openSession(true);
                    bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.print("请输入你要查询的图书编号: ");
                    String bookId = scanner.nextLine();
                    BookInfo bookInfo = bookInfoMapper.queryBookInfoById(bookId);
                    logger.info("Select = {}", bookInfo);
                    sqlSession.close();
                    break;
                // 根据图书名称查询图书信息
                case 3:
                    sqlSession = sqlSessionFactory.openSession(true);
                    bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.print("请输入你要查询的图书名称: ");
                    String bookName = scanner.nextLine();
                    bookInfo = bookInfoMapper.queryBookInfoByName(bookName);
                    logger.info("Select = {}", bookInfo);
                    sqlSession.close();
                    break;
                // 增加图书信息条目
                case 4:
                    sqlSession = sqlSessionFactory.openSession(true);
                    bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
                    BookInfo bookInfo1 = new BookInfo();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入你要添加的图书信息: ");
                    System.out.print("图书编号: ");
                    bookInfo1.setBookId(scanner.nextLine());
                    System.out.print("图书名称: ");
                    bookInfo1.setBookName(scanner.nextLine());
                    System.out.print("图书类型: ");
                    String input = scanner.nextLine();
                    bookInfo1.setBookType(Enum.valueOf(BookType.class, input));
                    System.out.print("图书作者: ");
                    bookInfo1.setAuthor(scanner.nextLine());
                    System.out.print("图书出版社: ");
                    bookInfo1.setPublishingHouse(scanner.nextLine());
                    System.out.print("图书价格: ");
                    bookInfo1.setPrice(scanner.nextDouble());
                    int effect = bookInfoMapper.insertBookInfo(bookInfo1);
                    logger.info("Insert Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 根据图书编号删除图书信息
                case 5:
                    sqlSession = sqlSessionFactory.openSession(true);
                    bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
                    System.out.print("请输入你要删除的图书信息的图书编号: ");
                    bookId = scanner.nextLine();
                    effect = bookInfoMapper.deleteBookInfoById(bookId);
                    logger.info("Delete Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 更新图书信息
                case 6:
                    sqlSession = sqlSessionFactory.openSession(true);
                    bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
                    BookInfo bookInfo2 = new BookInfo();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入你要更新的图书信息: ");
                    System.out.print("需要更新的图书编号: ");
                    bookInfo2.setBookId(bookId = scanner.nextLine());
                    System.out.print("图书名称更新为: ");
                    bookInfo2.setBookName(scanner.nextLine());
                    System.out.print("图书类型更新为: ");
                    input = scanner.nextLine();
                    bookInfo2.setBookType(Enum.valueOf(BookType.class, input));
                    System.out.print("图书作者更新为: ");
                    bookInfo2.setAuthor(scanner.nextLine());
                    System.out.print("图书出版社更新为: ");
                    bookInfo2.setPublishingHouse(scanner.nextLine());
                    System.out.print("图书价格为: ");
                    bookInfo2.setPrice(scanner.nextDouble());
                    effect = bookInfoMapper.insertBookInfo(bookInfo2);
                    logger.info("Insert Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 查询所有的读者信息
                case 7:
                    sqlSession = sqlSessionFactory.openSession(true);
                    UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
                    List<UserInfo> userInfoList = userInfoMapper.queryUserInfoAll();
                    logger.info("Select result = {}", userInfoList);
                    sqlSession.close();
                    break;
                // 根据读者编号查询读者信息
                case 8:
                    sqlSession = sqlSessionFactory.openSession(true);
                    userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.print("请输入你要查询的读者编号: ");
                    String userId = scanner.nextLine();
                    UserInfo userInfo = userInfoMapper.queryUserInfoById(userId);
                    logger.info("Select = {}", userInfo);
                    sqlSession.close();
                    break;
                // 插入读者信息条目
                case 9:
                    sqlSession = sqlSessionFactory.openSession(true);
                    userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
                    UserInfo userInfo1 = new UserInfo();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入你要添加的读者信息: ");
                    System.out.print("读者编号: ");
                    userInfo1.setUserId(bookId = scanner.nextLine());
                    System.out.print("读者姓名: ");
                    userInfo1.setUserName(scanner.nextLine());
                    System.out.print("读者性别: ");
                    input = scanner.nextLine().toUpperCase();
                    userInfo1.setUserGender(Enum.valueOf(UserGender.class, input));
                    System.out.print("读者电话: ");
                    userInfo1.setTelephone(scanner.nextLine());
                    System.out.print("读者所在院系: ");
                    userInfo1.setInstitute(scanner.nextLine());
                    System.out.print("读者所在班级: ");
                    userInfo1.setUserClass(scanner.nextLine());
                    System.out.print("图书证号: ");
                    userInfo1.setBookCardId(scanner.nextLine());
                    effect = userInfoMapper.insertUserInfo(userInfo1);
                    logger.info("Insert Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 根据读者编号删除读者信息
                case 10:
                    sqlSession = sqlSessionFactory.openSession(true);
                    userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.print("请输入要删除的读者信息的编号: ");
                    userId = scanner.nextLine();
                    effect = userInfoMapper.deleteUserInfoById(userId);
                    logger.info("Delete Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 更新读者信息
                case 11:
                    sqlSession = sqlSessionFactory.openSession(true);
                    userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
                    UserInfo userInfo2 = new UserInfo();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入你要更新的读者信息: ");
                    System.out.print("需要更新的读者编号: ");
                    userInfo2.setUserId(bookId = scanner.nextLine());
                    System.out.print("读者姓名更新为: ");
                    userInfo2.setUserName(scanner.nextLine());
                    System.out.print("读者性别更新为: ");
                    input = scanner.nextLine().toUpperCase();
                    userInfo2.setUserGender(Enum.valueOf(UserGender.class, input));
                    System.out.print("读者电话更新为: ");
                    userInfo2.setTelephone(scanner.nextLine());
                    System.out.print("读者所在院系更新为: ");
                    userInfo2.setInstitute(scanner.nextLine());
                    System.out.print("读者所在班级更新为: ");
                    userInfo2.setUserClass(scanner.nextLine());
                    System.out.print("图书证号更新为: ");
                    userInfo2.setBookCardId(scanner.nextLine());
                    effect = userInfoMapper.updateUserInfoByObject(userInfo2);
                    logger.info("Update Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 查询所有的图书借阅信息
                case 12:
                    sqlSession = sqlSessionFactory.openSession(true);
                    BorrowInfoMapper borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
                    List<BorrowInfo> borrowInfoList = borrowInfoMapper.queryBorrowInfoAll();
                    logger.info("Select result = {}", borrowInfoList);
                    sqlSession.close();
                    break;
                // 通过图书编号查询借阅信息
                case 13:
                    sqlSession = sqlSessionFactory.openSession(true);
                    borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.println("请输入图书编号查询借阅信息: ");
                    bookId = scanner.nextLine();
                    BorrowInfo borrowInfo = borrowInfoMapper.queryBorrowInfoByBookId(bookId);
                    break;
                // 更新借阅信息
                case 14:
                    sqlSession = sqlSessionFactory.openSession(true);
                    borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
                    BorrowInfo borrowInfo1 = new BorrowInfo();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入你要更新的借阅信息: ");
                    System.out.print("需要更新的图书编号: ");
                    borrowInfo1.setBookId(bookId = scanner.nextLine());
                    System.out.print("图书证号更新为: ");
                    borrowInfo1.setBookCardId(scanner.nextLine());
                    System.out.print("读者编号更新为: ");
                    borrowInfo1.setUserId(scanner.nextLine());
                    System.out.print("读者姓名更新为: ");
                    borrowInfo1.setUserName(scanner.nextLine());
                    System.out.print("读者借书时间更新为: "+LocalDate.now()+"\n");
                    borrowInfo1.setBorrowTime(LocalDate.now());
                    LocalDate borrowTime = LocalDate.now();
                    // 还书期限为30天
                    LocalDate deadlineTime = borrowTime.plus(30, ChronoUnit.DAYS);
                    System.out.print("读者还书时间更新为: "+deadlineTime+"\n");
                    borrowInfo1.setDeadlineTime(deadlineTime);
                    System.out.print("是否续借更新为: ");
                    borrowInfo1.setRenew(scanner.nextBoolean());
                    effect = borrowInfoMapper.updateBorrowInfoByObject(borrowInfo1);
                    logger.info("Update Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 通过读者编号查询借阅信息
                case 15:
                    sqlSession = sqlSessionFactory.openSession(true);
                    borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.print("请输入读者编号查阅其借阅信息: ");
                    userId = scanner.nextLine();
                    borrowInfoList = borrowInfoMapper.queryBorrowInfoByUserId(userId);
                    logger.info("Select result = {}", borrowInfoList);
                    sqlSession.close();
                    break;
                // 插入借阅信息条目
                case 16:
                    sqlSession = sqlSessionFactory.openSession(true);
                    borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
                    BorrowInfo borrowInfo2 = new BorrowInfo();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入你要添加的借阅信息: ");
                    System.out.print("图书编号: ");
                    borrowInfo2.setBookId(bookId = scanner.nextLine());
                    System.out.print("图书证号: ");
                    borrowInfo2.setBookCardId(scanner.nextLine());
                    System.out.print("读者编号: ");
                    borrowInfo2.setUserId(scanner.nextLine());
                    System.out.print("读者姓名: ");
                    borrowInfo2.setUserName(scanner.nextLine());
                    System.out.print("读者借书时间: "+LocalDate.now()+"\n");
                    borrowInfo2.setBorrowTime(LocalDate.now());
                    borrowTime = LocalDate.now();
                    // 还书期限为30天
                    deadlineTime = borrowTime.plus(30, ChronoUnit.DAYS);
                    System.out.print("读者还书时间更新为: "+deadlineTime+"\n");
                    borrowInfo2.setDeadlineTime(deadlineTime);
                    System.out.print("是否续借: ");
                    borrowInfo2.setRenew(scanner.nextBoolean());
                    effect = borrowInfoMapper.insertBorrowInfo(borrowInfo2);
                    logger.info("Insert Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 根据图书编号删除借阅信息
                case 17:
                    sqlSession = sqlSessionFactory.openSession(true);
                    borrowInfoMapper = sqlSession.getMapper(BorrowInfoMapper.class);
                    scanner = new Scanner(System.in);
                    System.out.print("请输入你要删除的图书编号对应的借阅信息: ");
                    bookId = scanner.nextLine();
                    effect = borrowInfoMapper.deleteBorrowInfoByBookId(bookId);
                    logger.info("Delete Result =  {}", effect);
                    sqlSession.close();
                    break;
                // 退出系统
                case 0:
                    System.out.println("感谢您的使用...");
                    break;
                default:
                    System.out.println("选择错误...");
                    break;
            }
        }
    }
}

public class BooksManagementApplication {

    public static void main(String[] args) {
        new test();
    }

}
