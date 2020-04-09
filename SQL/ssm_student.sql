/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : localhost:3306
 Source Schema         : ssm_student

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 09/04/2020 19:30:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `gender` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `portraitPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '存储头像的项目路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1, 'admin', '男', 'admin', '15052342456@qq.com', '15052342456', '南京晓庄学院', '0433bc9e985149c9b6d5b9851cd2cec2.jpg');
INSERT INTO `tb_admin` VALUES (2, '小智', '男', '1999', '278191281@qq.com', '15927272677', '江苏南京', '8fb77c7722d34350b26e15220b6dabc8.jpg');
INSERT INTO `tb_admin` VALUES (19, 'smallz', '男', 'smallz', 'smallz@qq.com', '15050515678', '江苏南京', '100b69ba0af4477498430c82250cd6de.jpg');
INSERT INTO `tb_admin` VALUES (21, 'zyj', '男', 'zyj', '13625152161@qq.com', '13625152161', '江苏盐城', '758de62398af406f8bd1f1dd403f1e81.jpg');
INSERT INTO `tb_admin` VALUES (24, '张三', '男', 'zhangsan', 'zhangsan@qq.com', '13908789765', '南京', '2512b45a6d46444abdba80fde3235f75.jpg');

-- ----------------------------
-- Table structure for tb_clazz
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `number` int(3) NOT NULL COMMENT '班级人数',
  `introduction` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级介绍',
  `teacherName` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班主任/辅导员项目',
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `gradeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属年级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
INSERT INTO `tb_clazz` VALUES (1, '软件工程4', 28, '大学三年级，软件工程班', '尹老师', '13456621156', 'yin@qq.com', '17级');
INSERT INTO `tb_clazz` VALUES (2, '计算机科学与技术4', 45, '大四', '王总', '13908982821', 'wan@qq.com', '16级');
INSERT INTO `tb_clazz` VALUES (3, '软件工程5', 30, '2017级软件工程专业', '尹老师', '13456621156', 'yin@qq.com', '17级');
INSERT INTO `tb_clazz` VALUES (4, '数学师范4班', 33, '2018届数学师范4班', '宋老师', '15256271918', '15256271918@139.com', '18级');
INSERT INTO `tb_clazz` VALUES (5, '数学师范13班', 22, '数学师范13', '张老师', '13629990715', 'zhang@qq.com', '17级');

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `manager` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introduction` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES (1, '17级', '杨主任', '10086@qq.com', '15141225552', '2017级');
INSERT INTO `tb_grade` VALUES (2, '18级', '李四', '28819@qq.com', '13452656123', '2018级');
INSERT INTO `tb_grade` VALUES (3, '16级', '王五', 'wangwu@qq.com', '13907281982', '2016级');
INSERT INTO `tb_grade` VALUES (4, '19级', '张主任', '15152682672@139.com', '15152682672', '2019级');
INSERT INTO `tb_grade` VALUES (5, '20级', '周主任', '13902652718@163.com', '13902652718', '2020级');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `portraitPath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `clazzName` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tb_student_sno_uindex`(`sno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (1, '17130316', 'smallz', '男', 'smallz', 'smallZ@qq.com', '15151831000', '江苏南京', '大学三年级学生', '66a50adf73064dc4897b349f14f31cd5.jpg', '软件工程4');
INSERT INTO `tb_student` VALUES (5, '18130101', '王五', '男', '000000', '26373278321@qq.com', '15218914563', '南京晓庄学院', '18级', '', '计算机科学与技术4');
INSERT INTO `tb_student` VALUES (6, '19029228', '测试用例01', '女', '000000', '13902907654@139.com', '13902907654', '南京', '', '', '软件工程5');
INSERT INTO `tb_student` VALUES (7, '18818111', '测试用例02', '男', '11111', '14008278171@qq.com', '14008278171', '徐州', '', '', '软件工程4');
INSERT INTO `tb_student` VALUES (8, '17176189', '测试用例3', '男', '000000', '17176189@qq.com', '12716371211', '南京', '软件工程5', '', '软件工程5');
INSERT INTO `tb_student` VALUES (9, '19029092', '张三', '男', '19029092', '19029092@qq.com', '19029092213', '南京', '计算机科学与技术4', '', '计算机科学与技术4');
INSERT INTO `tb_student` VALUES (11, '17130231', '张无忌', '男', '100100', 'zwj@qq.com', '12188199200', '武当山', '', 'fa98e26e7c8c4d48b0ba558ace068e7e.jpg', '软件工程5');
INSERT INTO `tb_student` VALUES (12, '17190927', '周芷若', '女', '10010', '17190927@qq.com', '13902921567', '峨眉山', '峨眉山掌门', '5df71d120ae643b68632949bfe8908db.jpg', '软件工程5');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `portraitPath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `clazzName` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tb_teacher_tno_uindex`(`tno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (1, '100100', '王老师', '男', '10086', 'wang@qq.com', '13903891789', '江苏南京', '5e676bcbaa4649f0bc770db033d8cd29.jpg', '软件工程4');
INSERT INTO `tb_teacher` VALUES (2, '177110', '张玉杰', '男', 'ZYJ', 'zhang@qq.com', '13124152413', '江苏盐城', '531679618c6b46b884b87f92fe89e36c.jpg', '软件工程4');
INSERT INTO `tb_teacher` VALUES (3, '100111', '杨老师', '女', '100111', '100111@qq.com', '13902929832', '江苏南京', '', '软件工程5');
INSERT INTO `tb_teacher` VALUES (4, '000001', 'teacher', '男', 'teacher', 'teacher@qq.com', '13678906345', '江苏南京', '5c045214e2364ee894cb9482feab21be.jpg', NULL);
INSERT INTO `tb_teacher` VALUES (5, '100110', '朱老师', '女', '100110', 'Teacher_zhu@qq.com', '15050525243', '南京晓庄学院', '1403ef515a4b420ea7b6644209e12086.jpg', '软件工程4');

SET FOREIGN_KEY_CHECKS = 1;
