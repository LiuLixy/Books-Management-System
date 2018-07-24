drop database if exists `books_management`;
create database if not exists `books_management` default character set utf8 collate utf8_general_ci;

use books_management;

-- 读者信息表
drop table if exists `user_info`;
create table if not exists `user_info` (
	user_id varchar(32) primary key comment '读者学号',
	user_name varchar(32) not null comment '读者姓名',
	gender varchar(8) not null comment '读者性别', 
	telephone varchar(20) not null comment '读者联系方式',
	institute varchar(32) comment '读者所在院系',
	user_class varchar(32) not null comment '读者所在班级',
	book_card_id varchar(32) not null comment '图书证号'
)engine innodb;

-- 图书信息表
drop table if exists `book_info`;
create table if not exists `book_info` (
	book_id varchar(32) primary key comment '图书编号',
	book_name varchar(32) not null comment '书名',
	book_type varchar(32) not null comment '图书类别',
	author varchar(32) not null comment '作者',
	publishing_house varchar(32) not null comment '出版社',
	price float(5, 2) unsigned comment '图书价格'  
)engine innodb;

-- 图书借阅表
drop table if exists `borrow_info`;
create table if not exists `borrow_info` (
	book_id varchar(32) primary key comment '图书编号',
	book_card_id varchar(32) not null comment '图书证号',
	user_id varchar(32) not null comment '读者学号',
	user_name varchar(32) not null comment '读者姓名',
	borrow_time datetime not null comment '借书日期',
	deadline_time datetime not null comment '还书期限',
	is_renew char(1) not null comment '是否续借, 0:不续借, 1:续借'
)engine innodb;