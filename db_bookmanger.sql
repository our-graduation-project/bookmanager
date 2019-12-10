/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : db_bookmanger

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 09/12/2019 17:30:46
*/

drop database if exists `db_book_manager`;
create database `db_book_manager`;
use db_book_manager;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announcement_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `announcement_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `announcement_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `publish_date` date NOT NULL COMMENT '发布日期',
  `is_delete` int(11) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`announcement_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `book_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图书名称',
  `book_isbn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书号',
  `book_type` int(11) NOT NULL COMMENT '图书类型，参考图书类型表',
  `book_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `author` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '作者',
  `introduce` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书简介',
  `price` double(10, 2) NOT NULL COMMENT '价格',
  `book_count` int(11) NOT NULL COMMENT '总数量',
  `book_num` int(11) NOT NULL COMMENT '在馆数量',
  `bookshelf` int(11) NOT NULL COMMENT '所属书架，参考书架表',
  PRIMARY KEY (`book_id`) USING BTREE,
  UNIQUE INDEX `book_isbn`(`book_isbn`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bookshelf
-- ----------------------------
DROP TABLE IF EXISTS `bookshelf`;
CREATE TABLE `bookshelf`  (
  `bookshelf_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookshelf_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书架名',
  `bookshelf_position` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书架位置',
  PRIMARY KEY (`bookshelf_id`) USING BTREE,
  UNIQUE INDEX `bookshelf_name`(`bookshelf_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书类型名',
  PRIMARY KEY (`type_id`) USING BTREE,
  UNIQUE INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for borrow_detail
-- ----------------------------
DROP TABLE IF EXISTS `borrow_detail`;
CREATE TABLE `borrow_detail`  (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `book_id` int(11) NOT NULL COMMENT '书籍ID',
  `borrow_date` datetime(0) NOT NULL COMMENT '借阅日期',
  `should_return_date` datetime(0) NOT NULL COMMENT '应还日期',
  `real_return_date` datetime(0) NULL DEFAULT NULL COMMENT '实还日期',
  `fine` double(10, 2) NULL DEFAULT NULL COMMENT '罚金',
  `status` int(11) NOT NULL COMMENT '状态(1.借出未还 2.期限内还 3.逾期还 4.逾期未还）',
  PRIMARY KEY (`borrow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `label_id` int(11) NOT NULL COMMENT '标签ID',
  `label_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名',
  PRIMARY KEY (`label_id`) USING BTREE,
  UNIQUE INDEX `label_name`(`label_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for label_relation
-- ----------------------------
DROP TABLE IF EXISTS `label_relation`;
CREATE TABLE `label_relation`  (
  `labelrelation_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `label_id` int(11) NOT NULL,
  PRIMARY KEY (`labelrelation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lose
-- ----------------------------
DROP TABLE IF EXISTS `lose`;
CREATE TABLE `lose`  (
  `lose_id` int(11) NOT NULL COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `book_id` int(11) NOT NULL COMMENT '书籍ID',
  `lose_date` datetime(0) NOT NULL COMMENT '挂失日期',
  `fine` double(10, 0) NOT NULL COMMENT '罚金',
  PRIMARY KEY (`lose_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `manager_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名',
  `manager_nickname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员昵称',
  `mailbox` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `manager_pwd` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `sex` int(11) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  PRIMARY KEY (`manager_id`) USING BTREE,
  UNIQUE INDEX `manager_nickname`(`manager_nickname`) USING BTREE,
  UNIQUE INDEX `mailbox`(`mailbox`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve`  (
  `reserve_id` int(11) NOT NULL COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `book_id` int(11) NOT NULL COMMENT '书籍ID',
  `reserve_date` datetime(0) NOT NULL COMMENT '预定时间',
  PRIMARY KEY (`reserve_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `mailbox` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `user_pwd` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `sex` int(11) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE,
  UNIQUE INDEX `mailbox`(`mailbox`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
