/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : db_future

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-11-29 13:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `certificate`
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `path` varchar(200) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ce_tiden` (`tid`),
  CONSTRAINT `certificate_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `tutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of certificate
-- ----------------------------

-- ----------------------------
-- Table structure for `contact`
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `email` varchar(320) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_piden` (`pid`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------

-- ----------------------------
-- Table structure for `enterprise`
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `website` varchar(100) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `trademark` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `lables` varchar(100) DEFAULT NULL,
  `des` varchar(2500) DEFAULT NULL,
  `lev` varchar(2) DEFAULT NULL,
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES ('1', '123', 'kaijie', null, 'kunshan', '咨询，IT', 'hhsfajflasjlfasdfjlkajflkajslfjaslkfjlksajflksadjflksajflksdajflksajflkjlfjlskafjlksdajflkajflksdjalfkjlskafjlk', '1', '0');
INSERT INTO `enterprise` VALUES ('2', 'www.mrriok.com', 'Mrriok', '', '', '咨询，IT', '未来可期', '2', '0');
INSERT INTO `enterprise` VALUES ('3', 'www.chuizi.com', '锤子', '', '', '手机', '挺好', '2', '0');
INSERT INTO `enterprise` VALUES ('4', 'aa', 'aa', '', '', 'aa', 'aa', '2', '1');
INSERT INTO `enterprise` VALUES ('5', 'ff', 'ff', '', '', 'ff', 'ff', '2', '1');

-- ----------------------------
-- Table structure for `honor`
-- ----------------------------
DROP TABLE IF EXISTS `honor`;
CREATE TABLE `honor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuid` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `company` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `h_stuiden` (`stuid`),
  CONSTRAINT `honor_ibfk_1` FOREIGN KEY (`stuid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of honor
-- ----------------------------

-- ----------------------------
-- Table structure for `loginlog`
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `loginId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `loginTime` datetime NOT NULL,
  `ifSuccess` int(11) NOT NULL,
  `loginUserIp` varchar(100) NOT NULL,
  `loginDesc` varchar(200) NOT NULL,
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`loginId`),
  KEY `userId` (`userId`),
  CONSTRAINT `loginlog_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginlog
-- ----------------------------
INSERT INTO `loginlog` VALUES ('1', '1', '2019-09-29 17:05:31', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('2', '1', '2019-09-29 17:28:54', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('3', '1', '2019-09-29 17:31:32', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('4', '1', '2019-09-29 17:35:21', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('5', '1', '2019-09-29 17:37:01', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('6', '1', '2019-09-30 09:24:00', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('8', '1', '2019-09-30 09:58:37', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('9', '1', '2019-09-30 10:01:16', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('10', '1', '2019-09-30 10:07:30', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('11', '1', '2019-09-30 10:08:26', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('12', '1', '2019-09-30 10:09:41', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('13', '1', '2019-09-30 10:10:27', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('14', '1', '2019-09-30 10:12:33', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('15', '1', '2019-09-30 10:12:52', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('16', '1', '2019-09-30 10:14:38', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('17', '1', '2019-09-30 10:22:37', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('18', '1', '2019-09-30 10:26:49', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('19', '1', '2019-09-30 10:29:46', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('20', '1', '2019-09-30 10:30:46', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('21', '1', '2019-09-30 10:37:01', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('22', '1', '2019-09-30 10:59:08', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('23', '1', '2019-09-30 11:07:45', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('24', '1', '2019-09-30 11:20:35', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('25', '1', '2019-09-30 11:26:06', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('26', '1', '2019-09-30 11:27:44', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('27', '1', '2019-09-30 11:28:55', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('28', '1', '2019-09-30 11:38:35', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('29', '1', '2019-09-30 11:42:13', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('30', '1', '2019-09-30 12:25:17', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('31', '1', '2019-09-30 12:27:05', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('32', '1', '2019-09-30 13:03:54', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('33', '1', '2019-09-30 13:07:21', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('34', '1', '2019-09-30 13:12:45', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('35', '1', '2019-09-30 15:00:31', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('36', '1', '2019-09-30 15:57:01', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('38', '1', '2019-09-30 16:43:37', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('39', '1', '2019-09-30 17:14:06', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('40', '1', '2019-09-30 17:16:50', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('41', '1', '2019-09-30 17:24:18', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('43', '1', '2019-09-30 17:26:28', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('49', '0', '2019-09-30 17:33:00', '2', '127.0.0.1', '用户名与密码不匹配', '0');
INSERT INTO `loginlog` VALUES ('50', '1', '2019-09-30 17:33:20', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('51', '1', '2019-10-02 22:46:32', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('52', '1', '2019-10-02 22:49:02', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('53', '1', '2019-10-02 23:27:22', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('54', '1', '2019-10-02 23:31:09', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('55', '1', '2019-10-02 23:34:47', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('56', '1', '2019-10-02 23:37:07', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('57', '1', '2019-10-03 00:28:39', '1', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('58', '1', '2019-10-03 00:32:18', '1', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('59', '1', '2019-10-03 00:34:16', '1', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('60', '1', '2019-10-03 00:38:15', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('61', '1', '2019-10-03 00:42:31', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('62', '1', '2019-10-03 00:44:01', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('63', '0', '2019-10-03 13:34:25', '2', '127.0.0.1', '用户名与密码不匹配', '0');
INSERT INTO `loginlog` VALUES ('64', '1', '2019-10-03 13:34:41', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('65', '1', '2019-10-03 13:36:48', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('66', '1', '2019-10-03 15:38:51', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('67', '1', '2019-10-03 15:40:44', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('68', '1', '2019-10-03 15:42:46', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('69', '1', '2019-10-03 15:59:24', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('70', '1', '2019-10-03 16:07:56', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('71', '1', '2019-10-03 16:28:10', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('72', '1', '2019-10-03 16:38:55', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('73', '1', '2019-10-03 21:55:20', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('74', '1', '2019-10-03 22:04:29', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('75', '1', '2019-10-03 22:07:53', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('76', '1', '2019-10-03 22:09:09', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('77', '1', '2019-10-03 22:14:20', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('78', '1', '2019-10-03 22:20:24', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('79', '1', '2019-10-03 22:25:56', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('80', '1', '2019-10-03 22:54:18', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('81', '1', '2019-10-03 23:08:08', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('82', '1', '2019-10-03 23:11:16', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('83', '1', '2019-10-03 23:27:52', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('84', '1', '2019-10-03 23:32:57', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('85', '1', '2019-10-03 23:49:31', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('86', '1', '2019-10-03 23:51:37', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('87', '1', '2019-10-03 23:52:11', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('88', '1', '2019-10-03 23:53:41', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('89', '1', '2019-10-03 23:54:41', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('90', '1', '2019-10-03 23:57:57', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('91', '1', '2019-10-03 23:59:12', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('92', '1', '2019-10-04 00:01:14', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('93', '1', '2019-10-04 00:11:33', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('94', '1', '2019-10-04 00:21:46', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('95', '1', '2019-10-04 00:22:35', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('96', '1', '2019-10-04 11:46:17', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('97', '1', '2019-10-04 11:54:37', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('98', '1', '2019-10-04 11:58:19', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('99', '1', '2019-10-04 12:00:19', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('100', '1', '2019-10-04 12:01:47', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('101', '1', '2019-10-04 12:02:36', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('102', '1', '2019-10-04 12:05:01', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('103', '1', '2019-10-04 12:05:56', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('104', '1', '2019-10-04 12:10:56', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('105', '1', '2019-10-04 12:12:48', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('106', '1', '2019-10-04 12:19:49', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('107', '1', '2019-10-04 12:21:40', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('108', '1', '2019-10-04 12:22:52', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('109', '1', '2019-10-04 12:29:15', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('110', '1', '2019-10-04 12:31:17', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('111', '1', '2019-10-04 12:33:31', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('112', '1', '2019-10-04 12:52:03', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('113', '1', '2019-10-04 19:38:31', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('114', '1', '2019-10-04 12:01:12', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('115', '1', '2019-10-04 12:02:51', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('116', '1', '2019-10-04 12:28:41', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('117', '1', '2019-10-04 20:31:43', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('118', '1', '2019-10-04 12:38:31', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('119', '1', '2019-10-04 12:39:40', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('120', '1', '2019-10-04 20:40:05', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('121', '1', '2019-10-04 12:47:57', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('122', '0', '2019-10-05 03:31:23', '2', '123.164.236.183', '用户名与密码不匹配', '0');
INSERT INTO `loginlog` VALUES ('123', '0', '2019-10-05 03:31:25', '2', '123.164.236.183', '用户名与密码不匹配', '0');
INSERT INTO `loginlog` VALUES ('124', '0', '2019-10-05 03:31:27', '2', '123.164.236.183', '用户名与密码不匹配', '0');
INSERT INTO `loginlog` VALUES ('125', '1', '2019-10-05 03:32:17', '1', '123.164.236.183', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('126', '1', '2019-10-05 05:23:23', '1', '183.213.96.185', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('127', '1', '2019-11-27 14:13:25', '1', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('128', '1', '2019-11-27 20:06:48', '1', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('129', '1', '2019-11-27 20:16:42', '1', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('132', '0', '2019-11-28 11:07:10', '2', '0:0:0:0:0:0:0:1', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('133', '0', '2019-11-28 14:19:06', '2', '192.168.42.124', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('134', '0', '2019-11-28 14:19:47', '2', '192.168.42.124', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('135', '0', '2019-11-28 14:22:28', '2', '192.168.42.124', '用户名与密码不匹配', '0');
INSERT INTO `loginlog` VALUES ('136', '1', '2019-11-28 14:23:14', '1', '192.168.42.124', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('137', '1', '2019-11-28 18:37:07', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('138', '1', '2019-11-28 18:57:08', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('139', '0', '2019-11-28 19:04:16', '2', '0:0:0:0:0:0:0:1', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('140', '0', '2019-11-28 19:05:41', '2', '127.0.0.1', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('141', '1', '2019-11-28 19:06:03', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('142', '1', '2019-11-28 19:06:16', '1', '127.0.0.1', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('143', '0', '2019-11-28 21:17:38', '2', '0:0:0:0:0:0:0:1', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('144', '0', '2019-11-28 21:17:57', '2', '0:0:0:0:0:0:0:1', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('145', '0', '2019-11-28 21:18:29', '2', '0:0:0:0:0:0:0:1', '该用户不存在', '0');
INSERT INTO `loginlog` VALUES ('146', '1', '2019-11-28 21:48:52', '1', '192.168.42.178', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('147', '1', '2019-11-29 09:20:14', '1', '192.168.42.178', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('148', '1', '2019-11-29 09:20:19', '1', '192.168.42.178', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('149', '1', '2019-11-29 09:21:05', '1', '192.168.42.178', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('150', '1', '2019-11-29 09:39:44', '1', '192.168.42.178', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('151', '1', '2019-11-29 09:50:53', '1', '192.168.42.178', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('152', '1', '2019-11-29 10:06:58', '1', '192.168.42.229', '登录成功', '0');
INSERT INTO `loginlog` VALUES ('153', '1', '2019-11-29 13:11:38', '1', '192.168.42.137', '登录成功', '0');

-- ----------------------------
-- Table structure for `match`
-- ----------------------------
DROP TABLE IF EXISTS `match`;
CREATE TABLE `match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `stuid` int(11) NOT NULL,
  `result` varchar(10) DEFAULT NULL,
  `stp` varchar(200) DEFAULT NULL,
  `pts` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `m_position` (`pid`),
  KEY `m_stuiden` (`stuid`),
  CONSTRAINT `match_ibfk_2` FOREIGN KEY (`stuid`) REFERENCES `student` (`id`),
  CONSTRAINT `match_ibfk_3` FOREIGN KEY (`pid`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of match
-- ----------------------------

-- ----------------------------
-- Table structure for `operatelog`
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog` (
  `operateId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `operateName` varchar(50) NOT NULL,
  `cname` varchar(50) NOT NULL,
  `operateDesc` varchar(200) NOT NULL,
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  `operateTime` datetime NOT NULL,
  PRIMARY KEY (`operateId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operatelog
-- ----------------------------
INSERT INTO `operatelog` VALUES ('2', '1', 'addEnterprise', '哈哈', '执行的操作是：method: addEnterprise, 添加企业：ff', '0', '2019-10-04 11:55:36');
INSERT INTO `operatelog` VALUES ('3', '1', 'addTutor', '哈哈', '执行的操作是：method: addTutor, 添加导师：rr', '0', '2019-10-04 11:56:06');
INSERT INTO `operatelog` VALUES ('4', '1', 'deleteStudentById', '哈哈', '执行的操作是：method: deleteStudentById, 删除了学生：5', '0', '2019-10-04 11:56:20');
INSERT INTO `operatelog` VALUES ('5', '1', 'deleteTutorById', '哈哈', '执行的操作是：method: deleteTutorById,  删除了导师：10', '0', '2019-10-04 11:56:33');
INSERT INTO `operatelog` VALUES ('6', '1', 'deleteEnterpriseById', '哈哈', '执行的操作是：method: deleteEnterpriseById, 删除了企业：5', '0', '2019-10-04 11:57:50');
INSERT INTO `operatelog` VALUES ('10', '1', 'updateEnterprise', '哈哈', '执行的操作是：method: updateEnterprise, 更新企业：1-kaijie', '0', '2019-10-04 12:02:41');
INSERT INTO `operatelog` VALUES ('11', '1', 'updateTutor', '哈哈', '执行的操作是：method: updateTutor, 更新导师：1-水杯', '0', '2019-10-04 12:02:53');
INSERT INTO `operatelog` VALUES ('12', '1', 'updateStudent', '哈哈', '执行的操作是：method: updateStudent, 更新学生：2-$此用户未注册$', '0', '2019-10-04 12:03:04');
INSERT INTO `operatelog` VALUES ('13', '1', 'deleteEnterprises', '哈哈', '执行的操作是：method: deleteEnterprises, 删除了企业：5,4,', '0', '2019-10-04 12:41:23');
INSERT INTO `operatelog` VALUES ('14', '1', 'deleteStudents', '哈哈', '执行的操作是：method: deleteStudents, 删除了学生：3,', '0', '2019-10-04 12:46:57');
INSERT INTO `operatelog` VALUES ('15', '1', 'addStudent', '哈哈', '执行的操作是：method: addStudent, 添加学生：dd', '0', '2019-10-04 12:47:47');
INSERT INTO `operatelog` VALUES ('16', '1', 'deleteStudents', '哈哈', '执行的操作是：method: deleteStudents, 删除了学生：6,', '0', '2019-10-04 12:47:53');
INSERT INTO `operatelog` VALUES ('17', '1', 'deleteTutors', '哈哈', '执行的操作是：method: deleteTutors, 删除了导师：9,', '0', '2019-10-04 12:48:49');
INSERT INTO `operatelog` VALUES ('18', '1', 'addStudent', '哈哈', '执行的操作是：method: addStudent, 添加学生：xiaolu', '0', '2019-10-04 12:04:07');
INSERT INTO `operatelog` VALUES ('19', '1', 'updateStudent', '哈哈', '执行的操作是：method: updateStudent, 更新学生：7-$此用户未注册$', '0', '2019-10-04 12:10:33');
INSERT INTO `operatelog` VALUES ('20', '1', 'addStudent', '哈哈', '执行的操作是：method: addStudent, 添加学生：ss', '0', '2019-10-04 20:40:53');
INSERT INTO `operatelog` VALUES ('21', '1', 'deleteStudents', '哈哈', '执行的操作是：method: deleteStudents, 删除了学生：8,', '0', '2019-10-04 20:41:16');

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `eid` int(11) NOT NULL,
  `edu` varchar(100) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `languange` varchar(100) DEFAULT NULL,
  `agerange` varchar(60) DEFAULT NULL,
  `des` varchar(1000) DEFAULT NULL,
  `classify` varchar(60) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_enterprise` (`eid`),
  KEY `getpositionbyname` (`name`),
  CONSTRAINT `position_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', 'PM', '1', '本科', '2000', '昆山', '第二语言', '18-33', '随便', null, '2');

-- ----------------------------
-- Table structure for `recom`
-- ----------------------------
DROP TABLE IF EXISTS `recom`;
CREATE TABLE `recom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuid` int(11) NOT NULL,
  `tutorid` int(11) NOT NULL,
  `input` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `r_stuiden` (`stuid`),
  KEY `r_tutoriden` (`tutorid`),
  CONSTRAINT `recom_ibfk_1` FOREIGN KEY (`stuid`) REFERENCES `student` (`id`),
  CONSTRAINT `recom_ibfk_2` FOREIGN KEY (`tutorid`) REFERENCES `tutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recom
-- ----------------------------

-- ----------------------------
-- Table structure for `roleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) NOT NULL,
  `roleDesc` varchar(100) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('1', 'admin', '管理员');
INSERT INTO `roleinfo` VALUES ('2', 'tutor', '导师');
INSERT INTO `roleinfo` VALUES ('3', 'student', '学生');

-- ----------------------------
-- Table structure for `roleright`
-- ----------------------------
DROP TABLE IF EXISTS `roleright`;
CREATE TABLE `roleright` (
  `roleRightId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `nodeId` int(11) NOT NULL,
  PRIMARY KEY (`roleRightId`),
  KEY `roleId` (`roleId`),
  KEY `nodeId` (`nodeId`),
  CONSTRAINT `roleright_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roleinfo` (`roleId`),
  CONSTRAINT `roleright_ibfk_2` FOREIGN KEY (`nodeId`) REFERENCES `sysfun` (`nodeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleright
-- ----------------------------
INSERT INTO `roleright` VALUES ('1', '1', '1');
INSERT INTO `roleright` VALUES ('2', '1', '2');
INSERT INTO `roleright` VALUES ('3', '1', '3');
INSERT INTO `roleright` VALUES ('5', '1', '5');
INSERT INTO `roleright` VALUES ('6', '1', '6');
INSERT INTO `roleright` VALUES ('7', '2', '2');
INSERT INTO `roleright` VALUES ('8', '3', '3');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `bir` varchar(15) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `schtime` varchar(30) DEFAULT NULL,
  `idea` varchar(40) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `lev` varchar(2) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `ability` varchar(400) DEFAULT NULL,
  `des` varchar(1000) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `schname` varchar(100) DEFAULT NULL,
  `pol` varchar(20) DEFAULT NULL,
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `s_account` (`account`),
  KEY `roleId` (`userId`),
  KEY `tid` (`tid`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `tutor` (`id`),
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '3', '1', '1', '1995-11-05', '123456789', '123@qq.com', '123', '2016', '软件工程师', '江苏徐州', '1', '软件工程', 'web', '优秀', '1', '齐齐哈尔大学', null, '0');
INSERT INTO `student` VALUES ('2', '14', '1', '1', '2019-10-01', '123456789', '789@qq.com', '', '2019-10-16', '软件工程师', '江苏徐州', '2', '软件工程', '摄影', '一般', null, '燕山大学', '预备党员', '2');
INSERT INTO `student` VALUES ('3', '0', '1', '2', '2019-10-13', '123', '456', '', '2019-10-03', '安卓开发', '北京', '2', '计算机网络', '游戏', '很菜', null, '青岛', '群众', '1');
INSERT INTO `student` VALUES ('4', '0', '1', '2', '2019-10-23', '1222', 'ss', '', '2019-10-16', 'ss', 'ss', '2', 'ss', 'ss', 'ss', null, 'ss', 'ss', '1');
INSERT INTO `student` VALUES ('5', '0', '1', '2', '2019-10-01', '123', 'ss', '', '2019-10-08', 'dd', 'ss', '2', 'ss', 'ss', 'ss', null, 'ss', 'ss', '1');
INSERT INTO `student` VALUES ('6', '0', '1', '1', '', '', '', '', '', 'dd', '', '', '', '', '', null, '', '', '1');
INSERT INTO `student` VALUES ('7', '24', '1', '2', '2019-10-07', '123', '123', '', '2019-10-22', '生物制药', '北京', '1', '生物工程', '吃饭', '很优秀', null, '北大', '党员', '2');
INSERT INTO `student` VALUES ('8', '0', '1', '1', '2019-10-08', '23', 'we', '', '2019-10-22', 'ss', 'we', '2', 'we', 'we', 'we', null, 'we', 'we', '1');

-- ----------------------------
-- Table structure for `stuexp`
-- ----------------------------
DROP TABLE IF EXISTS `stuexp`;
CREATE TABLE `stuexp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuid` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `entname` varchar(60) NOT NULL,
  `job` varchar(100) NOT NULL,
  `desc` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `se_stuiden` (`stuid`),
  CONSTRAINT `stuexp_ibfk_1` FOREIGN KEY (`stuid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuexp
-- ----------------------------

-- ----------------------------
-- Table structure for `sysfun`
-- ----------------------------
DROP TABLE IF EXISTS `sysfun`;
CREATE TABLE `sysfun` (
  `nodeId` int(11) NOT NULL AUTO_INCREMENT,
  `dsiplayName` varchar(50) NOT NULL,
  `nodeURL` varchar(100) NOT NULL,
  `displayOrder` int(11) NOT NULL,
  `parentNodeId` int(11) NOT NULL,
  PRIMARY KEY (`nodeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysfun
-- ----------------------------
INSERT INTO `sysfun` VALUES ('1', '企业管理', '/home/enterprise', '1', '1');
INSERT INTO `sysfun` VALUES ('2', '导师管理', '/home/tutor', '2', '2');
INSERT INTO `sysfun` VALUES ('3', '学生管理', '/home/student', '3', '3');
INSERT INTO `sysfun` VALUES ('5', '登录日志', '/home/sys/loginlog', '41', '4');
INSERT INTO `sysfun` VALUES ('6', '操作日志', '/home/sys/operatelog', '42', '4');

-- ----------------------------
-- Table structure for `tutor`
-- ----------------------------
DROP TABLE IF EXISTS `tutor`;
CREATE TABLE `tutor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `sch` varchar(100) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `lev` varchar(2) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `edu` varchar(20) DEFAULT NULL,
  `pol` varchar(20) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `des` varchar(500) DEFAULT NULL,
  `isDeleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `tutor_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tutor
-- ----------------------------
INSERT INTO `tutor` VALUES ('1', '2', '齐齐哈尔大学', '教授', '123456789', '123456@qq.com', '1', '123', '1', '西南联大', '2', null, null, '0');
INSERT INTO `tutor` VALUES ('4', '12', 'string', 'string', 'string', 'string', '1', '0', '1', 'string', 'string', 'ss', 'aa', '2');
INSERT INTO `tutor` VALUES ('8', '0', 'ss', 'ss', '123', 'ss', '1', null, '2', 'ss', 'ss', '', null, '1');
INSERT INTO `tutor` VALUES ('9', '0', 'cc', 'cc', '11', 'cc', '2', null, '2', 'cc', 'cc', '', 'cc', '1');
INSERT INTO `tutor` VALUES ('10', '0', 'rr', 'rr', '22', 'rr', '2', null, '2', 'rr', 'rr', '', 'rr', '1');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(20) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `cname` varchar(50) DEFAULT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `identifier` (`identifier`),
  UNIQUE KEY `username` (`username`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roleinfo` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('0', '0', '0', '0', '0', '3');
INSERT INTO `userinfo` VALUES ('1', 'haha1', 'haha', '123', '哈哈', '1');
INSERT INTO `userinfo` VALUES ('2', 'tutor201910022', 'shuibei', '$2a$10$OEMpsx8Btm8nrSNSg25rque7V5XOvndL.8ApZacSVyYvAY49Y0k92', '水杯', '2');
INSERT INTO `userinfo` VALUES ('3', 'stu201910023', 'shoubiao', '$2a$10$OEMpsx8Btm8nrSNSg25rque7V5XOvndL.8ApZacSVyYvAY49Y0k92', '手表', '3');
INSERT INTO `userinfo` VALUES ('12', 'tutor2019100312', 'xiaofei', null, '$此用户未注册$', '2');
INSERT INTO `userinfo` VALUES ('14', 'stu2019100314', 'xiaoqiang', null, '$此用户未注册$', '3');
INSERT INTO `userinfo` VALUES ('24', 'stu2019100424', 'xiaolu', null, '$此用户未注册$', '3');

-- ----------------------------
-- Table structure for `verification`
-- ----------------------------
DROP TABLE IF EXISTS `verification`;
CREATE TABLE `verification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(6) NOT NULL,
  `rema` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tid` (`tid`),
  CONSTRAINT `verification_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `tutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of verification
-- ----------------------------

-- ----------------------------
-- Table structure for `workexp`
-- ----------------------------
DROP TABLE IF EXISTS `workexp`;
CREATE TABLE `workexp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `entname` varchar(60) CHARACTER SET utf8 NOT NULL,
  `job` varchar(60) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `we_tiden` (`tid`),
  CONSTRAINT `workexp_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `tutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of workexp
-- ----------------------------
