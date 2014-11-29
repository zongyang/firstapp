/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-11-29 17:39:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from` int(11) DEFAULT NULL,
  `to` int(11) DEFAULT NULL,
  `content` text,
  `recept` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `actions` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES ('1', '42', '43', 'hello world aabbccddeeffgghhhjj', null, '2014-11-28 16:44:49', '');
INSERT INTO `chat` VALUES ('2', '43', '42', 'sdasdasdasd', null, '2014-11-28 16:47:53', null);
INSERT INTO `chat` VALUES ('3', '42', '43', '张曾洋', null, '2014-11-28 16:48:38', null);
INSERT INTO `chat` VALUES ('4', '42', '43', '张ss曾洋', null, '2014-11-28 16:48:43', null);
INSERT INTO `chat` VALUES ('5', '42', '43', '张sssss曾洋', null, '2014-11-28 16:48:45', null);
INSERT INTO `chat` VALUES ('6', '42', '43', '张sssss曾洋ss', null, '2014-11-28 16:48:47', null);
INSERT INTO `chat` VALUES ('7', '43', '42', 'sdasd123张sssss曾洋ss', null, '2014-11-28 16:48:56', null);
INSERT INTO `chat` VALUES ('8', '43', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 16:48:58', null);
INSERT INTO `chat` VALUES ('9', '44', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:07', null);
INSERT INTO `chat` VALUES ('10', '44', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:15', null);
INSERT INTO `chat` VALUES ('11', '44', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:20', null);
INSERT INTO `chat` VALUES ('12', '44', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:22', null);
INSERT INTO `chat` VALUES ('13', '44', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:23', null);
INSERT INTO `chat` VALUES ('14', '44', '42', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:24', null);
INSERT INTO `chat` VALUES ('15', '42', '44', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:42', null);
INSERT INTO `chat` VALUES ('16', '42', '44', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:43', null);
INSERT INTO `chat` VALUES ('17', '42', '44', 'sdasd1ret23张sssss曾洋ss', null, '2014-11-28 20:01:44', null);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `from` int(50) DEFAULT NULL,
  `to` int(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('42', '43');
INSERT INTO `friend` VALUES ('42', '44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` text,
  `psw` text,
  `nickName` text,
  `online` text,
  `ip` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('42', 'zongyang@qq.com', '123456', '宗杨', '1', '127.0.0.1');
INSERT INTO `user` VALUES ('43', 'zhangzengyang@qq.com', '123456', '校长', '1', '127.0.0.1');
INSERT INTO `user` VALUES ('44', 'zzy@qq.com', '123456', 'qq', '1', '127.0.0.1');

-- ----------------------------
-- View structure for chat_view
-- ----------------------------
DROP VIEW IF EXISTS `chat_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `chat_view` AS SELECT t1.*, t2.email as fromName,t3.email as toName  from chat t1
INNER JOIN user t2 on t1.from=t2.id
INNER JOIN  user t3 on t1.to=t3.id ;

-- ----------------------------
-- View structure for friend_view
-- ----------------------------
DROP VIEW IF EXISTS `friend_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `friend_view` AS SELECT t1.*, t2.email as fromName,t3.email as toName  from friend t1
INNER JOIN user t2 on t1.from=t2.id
INNER JOIN  user t3 on t1.to=t3.id ;
