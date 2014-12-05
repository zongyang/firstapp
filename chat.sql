/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-12-02 02:15:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from` text,
  `to` text,
  `content` text,
  `recept` text,
  `time` datetime DEFAULT NULL,
  `actions` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES ('216', '42', '43', '你好，初次见面！', '', '2014-12-01 02:36:44', '');
INSERT INTO `chat` VALUES ('217', '42', '44', '你好，初次见面！', '', '2014-12-01 02:36:44', '');
INSERT INTO `chat` VALUES ('218', '42', '43', '你好，初次见面！', '', '2014-12-01 02:36:52', '');
INSERT INTO `chat` VALUES ('219', '42', '44', '你好，初次见面！', '', '2014-12-01 02:36:52', '');
INSERT INTO `chat` VALUES ('220', '42', '43', 'asd', '', '2014-12-01 03:35:55', '');
INSERT INTO `chat` VALUES ('221', '43', '42', '马蛋  死java\n', '', '2014-12-01 03:36:30', '');
INSERT INTO `chat` VALUES ('222', '43', '42', '马蛋  死java', '', '2014-12-01 03:36:40', '');
INSERT INTO `chat` VALUES ('223', '42', '43', '非常赞同你的意见!', '', '2014-12-01 03:37:15', '');
INSERT INTO `chat` VALUES ('224', '43', '42', '真的吗', '', '2014-12-01 03:37:24', '');
INSERT INTO `chat` VALUES ('225', '42', '43', '这不废话吗', '', '2014-12-01 03:37:37', '');
INSERT INTO `chat` VALUES ('226', '42', '43', '怎么callback不起作用了', '', '2014-12-01 03:38:53', '');
INSERT INTO `chat` VALUES ('227', '42', '43', '讨厌  你怎么能这么说人家\n不和你玩了\n', '', '2014-12-01 03:41:12', '');
INSERT INTO `chat` VALUES ('228', '43', '42', '奴家 不要', '', '2014-12-01 03:44:23', '');
INSERT INTO `chat` VALUES ('229', '42', '43', '要得  要的\n', '', '2014-12-01 03:44:32', '');
INSERT INTO `chat` VALUES ('230', '42', '43', '要啊\n', '', '2014-12-01 03:44:41', '');
INSERT INTO `chat` VALUES ('231', '43', '42', '试试事实上\n', '', '2014-12-01 03:45:39', '');
INSERT INTO `chat` VALUES ('232', '43', '42', '撒女\n', '', '2014-12-01 03:45:53', '');
INSERT INTO `chat` VALUES ('233', '42', '', 'dfdfg\n', '', '2014-12-01 03:47:48', '');
INSERT INTO `chat` VALUES ('234', '42', '', 'fgfdg\n', '', '2014-12-01 03:47:55', '');
INSERT INTO `chat` VALUES ('235', '42', '43', '\n', '', '2014-12-01 03:51:40', '');
INSERT INTO `chat` VALUES ('236', '42', '43', '\n', '', '2014-12-01 03:52:41', '');
INSERT INTO `chat` VALUES ('237', '42', '43', '\n', '', '2014-12-01 03:52:51', '');
INSERT INTO `chat` VALUES ('238', '42', '43', '12345\n', '', '2014-12-01 03:55:19', '');
INSERT INTO `chat` VALUES ('239', '42', '43', 'sad\n', '', '2014-12-01 03:59:22', '');
INSERT INTO `chat` VALUES ('240', '42', '43', 'sadad', '', '2014-12-01 03:59:45', '');
INSERT INTO `chat` VALUES ('241', '42', '43', 'asdasd<br />', '', '2014-12-01 04:01:29', '');
INSERT INTO `chat` VALUES ('242', '42', '43', 'asd', '', '2014-12-01 04:02:09', '');
INSERT INTO `chat` VALUES ('243', '43', '42', '撒旦<br />阿斯达<br />阿斯达', '', '2014-12-01 04:02:31', '');
INSERT INTO `chat` VALUES ('244', '43', '42', '123456788', '', '2014-12-01 04:40:32', '');
INSERT INTO `chat` VALUES ('245', '43', '42', 'asdasd vvfddfg<br />', '', '2014-12-01 04:40:43', '');
INSERT INTO `chat` VALUES ('246', '43', '', 'asiodhohj', '', '2014-12-01 04:42:50', '');
INSERT INTO `chat` VALUES ('247', '43', '', 'asdasdasd', '', '2014-12-01 04:42:56', '');
INSERT INTO `chat` VALUES ('248', '42', '43', 'ewdwerwerwer', '', '2014-12-01 04:43:14', '');
INSERT INTO `chat` VALUES ('249', '42', '43', 'werwerdfvcxbvbnn', '', '2014-12-01 04:43:18', '');
INSERT INTO `chat` VALUES ('250', '42', '43', 'dfgdfgdfg', '', '2014-12-01 04:43:24', '');
INSERT INTO `chat` VALUES ('251', '43', '42', 'dfgdfgdfg', '', '2014-12-01 04:43:31', '');
INSERT INTO `chat` VALUES ('252', '43', '42', 'dfgdfg', '', '2014-12-01 04:43:34', '');
INSERT INTO `chat` VALUES ('253', '43', '42', 'dfdfg', '', '2014-12-01 04:43:36', '');
INSERT INTO `chat` VALUES ('254', '43', '42', '什么，，，<br />', '', '2014-12-01 04:43:47', '');
INSERT INTO `chat` VALUES ('255', '42', '43', 'sadasd', '', '2014-12-01 04:43:50', '');
INSERT INTO `chat` VALUES ('256', '42', '43', '123123', '', '2014-12-01 04:43:54', '');
INSERT INTO `chat` VALUES ('257', '43', '42', '123123', '', '2014-12-01 04:44:00', '');
INSERT INTO `chat` VALUES ('258', '43', '42', '123123', '', '2014-12-01 04:44:03', '');
INSERT INTO `chat` VALUES ('259', '43', '42', '123123', '', '2014-12-01 04:44:04', '');
INSERT INTO `chat` VALUES ('260', '43', '42', '123123', '', '2014-12-01 04:44:05', '');
INSERT INTO `chat` VALUES ('261', '43', '42', '1223', '', '2014-12-01 04:44:06', '');
INSERT INTO `chat` VALUES ('262', '43', '42', '我耳边v', '', '2014-12-01 04:44:10', '');
INSERT INTO `chat` VALUES ('263', '43', '42', '撒大时代', '', '2014-12-01 04:45:05', '');
INSERT INTO `chat` VALUES ('264', '43', '42', 'sad撒旦', '', '2014-12-01 04:45:10', '');
INSERT INTO `chat` VALUES ('265', '43', '42', '阿什顿', '', '2014-12-01 04:45:11', '');
INSERT INTO `chat` VALUES ('266', '42', '43', 'asdasd', '', '2014-12-01 04:45:13', '');
INSERT INTO `chat` VALUES ('267', '42', '43', 'asdasd', '', '2014-12-01 04:45:15', '');
INSERT INTO `chat` VALUES ('268', '42', '43', 'adsasd', '', '2014-12-01 04:45:17', '');
INSERT INTO `chat` VALUES ('269', '42', '43', 'asdcsad', '', '2014-12-01 04:45:18', '');
INSERT INTO `chat` VALUES ('270', '42', '43', 'asd', '', '2014-12-01 04:45:19', '');
INSERT INTO `chat` VALUES ('271', '42', '43', '', '', '2014-12-01 04:45:19', '');
INSERT INTO `chat` VALUES ('272', '42', '43', '不用紧张  就测试而已啦', '', '2014-12-01 08:40:14', '');
INSERT INTO `chat` VALUES ('273', '42', '43', '你好像没有收到消息哦', '', '2014-12-01 08:41:56', '');
INSERT INTO `chat` VALUES ('274', '42', '43', '怎么这次又收到了  很奇怪的哦', '', '2014-12-01 08:44:18', '');
INSERT INTO `chat` VALUES ('275', '43', '42', '我也很无语啊', '', '2014-12-01 08:44:37', '');
INSERT INTO `chat` VALUES ('276', '43', '42', '真不知道是哪个天才开发的', '', '2014-12-01 08:45:00', '');
INSERT INTO `chat` VALUES ('277', '42', '43', '呵呵  。。。', '', '2014-12-01 08:45:09', '');
INSERT INTO `chat` VALUES ('278', '43', '42', '123', '', '2014-12-01 09:12:39', '');
INSERT INTO `chat` VALUES ('279', '43', '42', '<br />123', '', '2014-12-01 09:12:44', '');
INSERT INTO `chat` VALUES ('280', '43', '42', '123123', '', '2014-12-01 09:13:02', '');
INSERT INTO `chat` VALUES ('281', '43', '42', '123123', '', '2014-12-01 09:13:26', '');
INSERT INTO `chat` VALUES ('282', '43', '42', '<br />', '', '2014-12-01 09:13:32', '');
INSERT INTO `chat` VALUES ('283', '43', '42', '<br />', '', '2014-12-01 09:13:32', '');
INSERT INTO `chat` VALUES ('284', '43', '42', '<br />', '', '2014-12-01 09:13:32', '');
INSERT INTO `chat` VALUES ('285', '43', '42', '<br />', '', '2014-12-01 09:17:41', '');
INSERT INTO `chat` VALUES ('286', '43', '42', '<br />', '', '2014-12-01 09:25:33', '');
INSERT INTO `chat` VALUES ('287', '43', '42', '1234', '', '2014-12-01 09:27:20', '');
INSERT INTO `chat` VALUES ('288', '43', '42', 'dsf', '', '2014-12-01 09:27:29', '');
INSERT INTO `chat` VALUES ('289', '43', '42', '<br />asd', '', '2014-12-01 09:27:31', '');
INSERT INTO `chat` VALUES ('290', '43', '42', 'asdasd', '', '2014-12-01 09:27:35', '');
INSERT INTO `chat` VALUES ('291', '43', '42', 'asdasd', '', '2014-12-01 09:27:39', '');
INSERT INTO `chat` VALUES ('292', '43', '42', '<br />wqeqwe', '', '2014-12-01 09:27:47', '');
INSERT INTO `chat` VALUES ('293', '43', '42', 'qweqwe', '', '2014-12-01 09:27:50', '');
INSERT INTO `chat` VALUES ('294', '43', '42', 'asdasd', '', '2014-12-01 09:28:17', '');
INSERT INTO `chat` VALUES ('295', '43', '42', 'asdasd<br />', '', '2014-12-01 09:28:22', '');
INSERT INTO `chat` VALUES ('296', '43', '42', '<br />asdasd', '', '2014-12-01 09:28:23', '');
INSERT INTO `chat` VALUES ('297', '43', '42', 'asd<br />asdasd', '', '2014-12-01 09:28:25', '');
INSERT INTO `chat` VALUES ('298', '43', '42', '<br />', '', '2014-12-01 09:28:40', '');
INSERT INTO `chat` VALUES ('299', '43', '42', '<br />', '', '2014-12-01 09:28:43', '');
INSERT INTO `chat` VALUES ('300', '43', '42', '<br />', '', '2014-12-01 09:28:43', '');
INSERT INTO `chat` VALUES ('301', '43', '42', '<br />', '', '2014-12-01 09:28:43', '');
INSERT INTO `chat` VALUES ('302', '43', '42', '<br />', '', '2014-12-01 09:28:44', '');
INSERT INTO `chat` VALUES ('303', '43', '42', '<br />', '', '2014-12-01 09:28:44', '');
INSERT INTO `chat` VALUES ('304', '43', '42', '<br />', '', '2014-12-01 09:28:44', '');
INSERT INTO `chat` VALUES ('305', '43', '42', '<br />', '', '2014-12-01 09:28:44', '');
INSERT INTO `chat` VALUES ('306', '43', '42', '<br />', '', '2014-12-01 09:28:44', '');
INSERT INTO `chat` VALUES ('307', '43', '42', '<br />', '', '2014-12-01 09:28:46', '');
INSERT INTO `chat` VALUES ('308', '43', '42', '<br />asd', '', '2014-12-01 09:28:47', '');
INSERT INTO `chat` VALUES ('309', '43', '42', '<br />sad', '', '2014-12-01 09:28:49', '');
INSERT INTO `chat` VALUES ('310', '43', '42', 'asdasd', '', '2014-12-01 09:29:00', '');
INSERT INTO `chat` VALUES ('311', '43', '42', 'asdasd<br />', '', '2014-12-01 09:29:03', '');
INSERT INTO `chat` VALUES ('312', '43', '42', 'asdasd<br /><br />', '', '2014-12-01 09:29:04', '');
INSERT INTO `chat` VALUES ('313', '43', '42', 'asdasd<br /><br /><br />', '', '2014-12-01 09:29:05', '');
INSERT INTO `chat` VALUES ('314', '43', '42', 'asdasd<br /><br /><br /><br />', '', '2014-12-01 09:29:06', '');
INSERT INTO `chat` VALUES ('315', '43', '42', 'asdasd<br /><br /><br /><br /><br />', '', '2014-12-01 09:29:06', '');
INSERT INTO `chat` VALUES ('316', '43', '42', 'asdasd<br /><br /><br /><br /><br /><br />', '', '2014-12-01 09:29:06', '');
INSERT INTO `chat` VALUES ('317', '43', '42', 'asd', '', '2014-12-01 09:30:41', '');
INSERT INTO `chat` VALUES ('318', '43', '42', 'asdxc', '', '2014-12-01 09:31:02', '');
INSERT INTO `chat` VALUES ('319', '43', '42', '<br />asdasd', '', '2014-12-01 09:31:03', '');
INSERT INTO `chat` VALUES ('320', '43', '42', 'sdfsafd', '', '2014-12-01 09:31:41', '');
INSERT INTO `chat` VALUES ('321', '43', '42', '<br />dsf', '', '2014-12-01 09:31:42', '');
INSERT INTO `chat` VALUES ('322', '43', '42', '<br />dfssdf', '', '2014-12-01 09:31:43', '');
INSERT INTO `chat` VALUES ('323', '43', '42', '<br />sdf', '', '2014-12-01 09:31:44', '');
INSERT INTO `chat` VALUES ('324', '43', '42', '<br />dsf', '', '2014-12-01 09:31:44', '');
INSERT INTO `chat` VALUES ('325', '43', '42', '<br />dsf', '', '2014-12-01 09:31:45', '');
INSERT INTO `chat` VALUES ('326', '43', '42', '<br />dsf', '', '2014-12-01 09:31:45', '');
INSERT INTO `chat` VALUES ('327', '43', '42', '<br />dsf', '', '2014-12-01 09:31:46', '');
INSERT INTO `chat` VALUES ('328', '43', '42', '<br />sdf', '', '2014-12-01 09:31:46', '');
INSERT INTO `chat` VALUES ('329', '43', '42', '<br />dfsdfsdf', '', '2014-12-01 09:31:47', '');
INSERT INTO `chat` VALUES ('330', '43', '42', '<br />dsf', '', '2014-12-01 09:31:48', '');
INSERT INTO `chat` VALUES ('331', '43', '42', '<br />sdf', '', '2014-12-01 09:31:48', '');
INSERT INTO `chat` VALUES ('332', '43', '42', '<br />sdf', '', '2014-12-01 09:31:49', '');
INSERT INTO `chat` VALUES ('333', '43', '42', '<br />dsf', '', '2014-12-01 09:31:49', '');
INSERT INTO `chat` VALUES ('334', '43', '42', '<br />sdf', '', '2014-12-01 09:31:50', '');
INSERT INTO `chat` VALUES ('335', '43', '42', '<br />df', '', '2014-12-01 09:31:50', '');
INSERT INTO `chat` VALUES ('336', '43', '42', '<br />df', '', '2014-12-01 09:31:51', '');
INSERT INTO `chat` VALUES ('337', '43', '42', '<br />sdf', '', '2014-12-01 09:31:51', '');
INSERT INTO `chat` VALUES ('338', '43', '42', '<br />sdf', '', '2014-12-01 09:31:52', '');
INSERT INTO `chat` VALUES ('339', '43', '42', '<br />sdf', '', '2014-12-01 09:31:52', '');
INSERT INTO `chat` VALUES ('340', '43', '42', '<br />sdf', '', '2014-12-01 09:31:53', '');
INSERT INTO `chat` VALUES ('341', '43', '42', 'asdasd', '', '2014-12-01 09:33:24', '');
INSERT INTO `chat` VALUES ('342', '43', '42', '<br />asd', '', '2014-12-01 09:33:25', '');
INSERT INTO `chat` VALUES ('343', '43', '42', '<br />sad', '', '2014-12-01 09:33:25', '');
INSERT INTO `chat` VALUES ('344', '43', '42', '<br />asd', '', '2014-12-01 09:33:26', '');
INSERT INTO `chat` VALUES ('345', '43', '42', '<br />asd', '', '2014-12-01 09:33:27', '');
INSERT INTO `chat` VALUES ('346', '43', '42', '<br />asd', '', '2014-12-01 09:33:27', '');
INSERT INTO `chat` VALUES ('347', '43', '42', '<br />sad', '', '2014-12-01 09:33:28', '');
INSERT INTO `chat` VALUES ('348', '43', '42', '<br />asd', '', '2014-12-01 09:33:29', '');
INSERT INTO `chat` VALUES ('349', '43', '42', '<br />asd', '', '2014-12-01 09:33:31', '');
INSERT INTO `chat` VALUES ('350', '43', '42', '怎么总是有回车呢<br />', '', '2014-12-01 09:33:45', '');
INSERT INTO `chat` VALUES ('351', '43', '42', '不知道  <br />', '', '2014-12-01 09:33:48', '');
INSERT INTO `chat` VALUES ('352', '43', '42', '你才', '', '2014-12-01 09:33:55', '');
INSERT INTO `chat` VALUES ('353', '43', '42', 'sad', '', '2014-12-01 09:33:57', '');
INSERT INTO `chat` VALUES ('354', '43', '42', 'asdf', '', '2014-12-01 09:33:59', '');
INSERT INTO `chat` VALUES ('355', '43', '42', '心在没有', '', '2014-12-01 09:34:02', '');
INSERT INTO `chat` VALUES ('356', '43', '44', '优势字符集', '', '2014-12-01 11:50:37', '');
INSERT INTO `chat` VALUES ('357', '43', '44', '真不容易啊', '', '2014-12-02 02:07:01', '');
INSERT INTO `chat` VALUES ('358', '44', '43', '我也有同感', '', '2014-12-02 02:07:27', '');
INSERT INTO `chat` VALUES ('359', '43', '44', '刚刚你怎么没收到消息啊', '', '2014-12-02 02:07:51', '');
INSERT INTO `chat` VALUES ('360', '44', '43', '哦 不清楚 系统不行嘛', '', '2014-12-02 02:08:08', '');
INSERT INTO `chat` VALUES ('361', '43', '44', '原来如此 怎么现在 有这么流畅了', '', '2014-12-02 02:08:28', '');
INSERT INTO `chat` VALUES ('362', '44', '43', '总共没几个人用吗', '', '2014-12-02 02:08:37', '');
INSERT INTO `chat` VALUES ('363', '43', '44', '逻辑都乱死了  肯定会出大问题的', '', '2014-12-02 02:09:05', '');
INSERT INTO `chat` VALUES ('364', '44', '43', '不是的吧', '', '2014-12-02 02:09:10', '');
INSERT INTO `chat` VALUES ('365', '43', '44', '可定会是的', '', '2014-12-02 02:09:17', '');
INSERT INTO `chat` VALUES ('366', '44', '43', '？？', '', '2014-12-02 02:09:21', '');
INSERT INTO `chat` VALUES ('367', '44', '43', '你打错字了', '', '2014-12-02 02:09:26', '');
INSERT INTO `chat` VALUES ('368', '43', '44', '。。。。。', '', '2014-12-02 02:09:33', '');
INSERT INTO `chat` VALUES ('369', '43', '44', '手误而已嘛', '', '2014-12-02 02:09:40', '');
INSERT INTO `chat` VALUES ('370', '43', '42', '还可以》', '', '2014-12-02 02:09:55', '');
INSERT INTO `chat` VALUES ('371', '43', '42', '真的还可以', '', '2014-12-02 02:10:05', '');

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
INSERT INTO `friend` VALUES ('44', '43');

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
