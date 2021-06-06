/*
Navicat MySQL Data Transfer

Source Server         : tyust
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : customer

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-06-01 16:22:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  `admin_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'name1', 'name1');
INSERT INTO `admin` VALUES ('2', 'name2', 'name2');
INSERT INTO `admin` VALUES ('3', 'name3', 'name3');
INSERT INTO `admin` VALUES ('10', 'my', 'my');
INSERT INTO `admin` VALUES ('11', 'my', 'my');
INSERT INTO `admin` VALUES ('12', 'my', 'my');

-- ----------------------------
-- Table structure for `c_customer`
-- ----------------------------
DROP TABLE IF EXISTS `c_customer`;
CREATE TABLE `c_customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of c_customer
-- ----------------------------
INSERT INTO `c_customer` VALUES ('25', '抽象', '男', '11111111111', '1@2.com', '梵蒂冈');
INSERT INTO `c_customer` VALUES ('26', '第三方', '男', '11111111111', '1@2.com', '阿斯顿');
INSERT INTO `c_customer` VALUES ('27', '撒旦', '男', '11111111111', '1@2.com', '阿斯顿');
INSERT INTO `c_customer` VALUES ('28', '你你你', '女', '11111111111', '1@2.com', '啊');
INSERT INTO `c_customer` VALUES ('29', '李凯', '女', '11111111111', '1@2.com', '范德萨');
INSERT INTO `c_customer` VALUES ('30', '李立', '女', '11111111111', '1@2.com', '的撒');
INSERT INTO `c_customer` VALUES ('31', '是否', '女', '11111111111', '1@2.com', '的撒');
INSERT INTO `c_customer` VALUES ('32', '但凡', '女', '11111111111', '1@2.com', '阿斯顿');
INSERT INTO `c_customer` VALUES ('33', '的撒', '男', '11111111111', '1@2.com', '撒旦');
INSERT INTO `c_customer` VALUES ('34', '的撒啊', '女', '11111111111', '1@2.com', '的撒');
INSERT INTO `c_customer` VALUES ('35', '我闷', '女', '11111111111', '1@2.com', '但凡');
INSERT INTO `c_customer` VALUES ('36', '的方式', '女', '11111111111', '1@2.com', '范德萨');

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
