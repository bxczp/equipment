/*
Navicat MySQL Data Transfer

Source Server         : root-ASUS
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : equipment

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-09-17 16:21:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptName` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '工程一部2', '工程一部。2');
INSERT INTO `t_department` VALUES ('2', '工程二部', '工程二部。');
INSERT INTO `t_department` VALUES ('3', '工程三部', '工程三部。');
INSERT INTO `t_department` VALUES ('4', '1', '②野人 ');
INSERT INTO `t_department` VALUES ('5', '2', '徒儿');
INSERT INTO `t_department` VALUES ('7', '4', '而又特然');
INSERT INTO `t_department` VALUES ('8', '5', '54y76b');
INSERT INTO `t_department` VALUES ('9', '6', '(⊙o⊙)…5y7e');
INSERT INTO `t_department` VALUES ('10', '7', '也让用户');
INSERT INTO `t_department` VALUES ('11', null, null);

-- ----------------------------
-- Table structure for t_equipment
-- ----------------------------
DROP TABLE IF EXISTS `t_equipment`;
CREATE TABLE `t_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `no` varchar(20) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`) USING BTREE,
  CONSTRAINT `t_equipment_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `t_equipmenttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_equipment
-- ----------------------------
INSERT INTO `t_equipment` VALUES ('1', '发电机', '001', '1', '3', '发电机。');
INSERT INTO `t_equipment` VALUES ('2', '电路板', '002', '2', '1', '电路板。');
INSERT INTO `t_equipment` VALUES ('6', '5', null, '1', '1', null);
INSERT INTO `t_equipment` VALUES ('7', '6', null, '1', '1', null);
INSERT INTO `t_equipment` VALUES ('8', '7', null, '1', '1', null);

-- ----------------------------
-- Table structure for t_equipmenttype
-- ----------------------------
DROP TABLE IF EXISTS `t_equipmenttype`;
CREATE TABLE `t_equipmenttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_equipmenttype
-- ----------------------------
INSERT INTO `t_equipmenttype` VALUES ('1', '强电型', '强电型。');
INSERT INTO `t_equipmenttype` VALUES ('2', '弱电型', '弱电型。');

-- ----------------------------
-- Table structure for t_repair
-- ----------------------------
DROP TABLE IF EXISTS `t_repair`;
CREATE TABLE `t_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equipmentId` int(11) DEFAULT NULL,
  `userman` varchar(20) DEFAULT NULL,
  `repairman` varchar(20) DEFAULT NULL,
  `repairTime` datetime DEFAULT NULL,
  `finishTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `finishState` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_repair
-- ----------------------------
INSERT INTO `t_repair` VALUES ('24', '1', 'jack', 'marry', '2015-02-02 16:26:50', '2015-02-02 16:26:59', '1', '2');
INSERT INTO `t_repair` VALUES ('25', '1', 'jack', 'marry', '2015-02-02 16:27:11', '2015-02-02 16:27:18', '1', '2');
INSERT INTO `t_repair` VALUES ('26', '1', 'jack', 'marry', '2015-02-02 16:27:31', '2015-02-02 16:27:35', '2', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deptId` (`deptId`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`deptId`) REFERENCES `t_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '11', '123456', 'bx', '管理员', '1');
INSERT INTO `t_user` VALUES ('2', 'jack', '123456', '杰克', '使用者', '2');
INSERT INTO `t_user` VALUES ('3', 'marry', '123456', '玛丽', '维修者', '3');
INSERT INTO `t_user` VALUES ('4', '1', null, null, null, '1');
INSERT INTO `t_user` VALUES ('5', '2', null, null, null, '1');
