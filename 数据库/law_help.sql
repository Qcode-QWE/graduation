/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : wisdom

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-08-04 21:29:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `law_help`
-- ----------------------------
DROP TABLE IF EXISTS `law_help`;
CREATE TABLE `law_help` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '咨询记录id',
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '一系列留言的标识id,为用户的id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '留言记录父id',
  `from_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '发送信息的用户id',
  `to_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '接收留言的用户id',
  `content` varchar(3000) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '留言详细内容',
  `from_mark` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:此条信息来自普通用户，2：此条信息来自工作人员',
  `reply_mark` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:此条信息待回复，2:此条信息已回复',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:数据正常，2：数据异常',
  `tag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:法律咨询，2：法律援助，3：司法救助',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间，时间戳格式',
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COMMENT='法律咨询，援助，救助 留言记录';

-- ----------------------------
-- Records of law_help
-- ----------------------------
INSERT INTO `law_help` VALUES ('1', '1', '0', '1', '0', '小明1的第一次咨询', '1', '2', '1', '1', '1564842700');
INSERT INTO `law_help` VALUES ('2', '1', '1', '1', '2', '第一次回复，阿迪斯发宋大哥阿迪斯发士大夫阿斯顿发射点阿斯顿干啥阿斯顿发生沙发上的 ', '2', '2', '1', '1', '1564842706');
INSERT INTO `law_help` VALUES ('3', '1', '2', '1', '0', '小明1的第二次咨询', '1', '2', '1', '1', '1564842750');
INSERT INTO `law_help` VALUES ('4', '1', '0', '1', '0', '小明1的第三次咨询', '1', '2', '1', '1', '1564842755');
INSERT INTO `law_help` VALUES ('5', '2', '0', '2', '0', 'Tom的第一次咨询', '1', '2', '1', '1', '1654650');
INSERT INTO `law_help` VALUES ('6', '2', '5', '1', '2', 'tom回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复', '2', '2', '1', '1', '1564912202');
INSERT INTO `law_help` VALUES ('7', '1', '4', '1', '1', '小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复', '2', '2', '1', '1', '1564912818');
INSERT INTO `law_help` VALUES ('8', '1', '0', '1', '0', '小明1的第四次咨询', '1', '2', '1', '1', '1564912820');
INSERT INTO `law_help` VALUES ('9', '1', '8', '1', '1', '小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复小明1的回复', '2', '2', '1', '1', '1564913619');
INSERT INTO `law_help` VALUES ('10', '1', '0', '1', '0', '小明1的第五次咨询', '1', '2', '1', '1', '1564913620');
INSERT INTO `law_help` VALUES ('11', '1', '0', '1', '0', '小明1的第五次咨询', '1', '2', '1', '1', '1564913621');
INSERT INTO `law_help` VALUES ('12', '3', '0', '3', '1', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('13', '4', '0', '4', '1', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('14', '4', '0', '4', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('15', '4', '0', '4', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('16', '5', '0', '5', '1', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('17', '5', '0', '5', '1', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('18', '5', '0', '5', '1', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('19', '5', '0', '5', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('20', '6', '0', '6', '1', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('21', '6', '0', '6', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('22', '7', '0', '7', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('23', '7', '0', '7', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('24', '8', '0', '8', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('25', '8', '0', '8', '0', '第一次咨询', '1', '2', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('26', '9', '0', '9', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('27', '9', '0', '9', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('28', '10', '0', '10', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('29', '10', '0', '10', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('30', '11', '0', '11', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('31', '11', '0', '11', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('33', '12', '0', '12', '0', '第一次咨询', '1', '1', '1', '1', '1564466755');
INSERT INTO `law_help` VALUES ('34', '4', '13', '1', '4', '小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复小S的回复', '2', '2', '1', '1', '1564921023');
INSERT INTO `law_help` VALUES ('35', '11', '0', '11', '0', '第一次援助', '1', '2', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('36', '12', '0', '12', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('37', '13', '0', '13', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('38', '14', '0', '14', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('39', '15', '0', '15', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('40', '16', '0', '16', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('41', '17', '0', '17', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('42', '18', '0', '18', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('43', '19', '0', '19', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('44', '20', '0', '20', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('45', '21', '0', '21', '0', '第一次援助', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('46', '22', '0', '22', '0', '第一次咨询', '1', '1', '1', '2', '1564466755');
INSERT INTO `law_help` VALUES ('47', '11', '35', '1', '11', '小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复小明7的回复', '2', '2', '1', '2', '1564922919');
INSERT INTO `law_help` VALUES ('48', '5', '16', '1', '5', '56阿斯蒂芬分公司的', '2', '2', '1', '1', '1564923021');
INSERT INTO `law_help` VALUES ('49', '1', '11', '1', '1', '小明1的第五次留言', '2', '2', '1', '1', '1564923293');
INSERT INTO `law_help` VALUES ('50', '5', '17', '1', '5', '小明3的回复小明3的回复小明3的回复小明3的回复小明3的回复小明3的回复小明3的回复小明3的回复小明3的回复', '2', '2', '1', '1', '1564923362');
INSERT INTO `law_help` VALUES ('51', '5', '18', '1', '5', '回复回复回复回复回复', '2', '2', '1', '1', '1564924203');
INSERT INTO `law_help` VALUES ('52', '1', '10', '1', '1', '小明1的第五次回复小明1的第五次回复小明1的第五次回复小明1的第五次回复小明1的第五次回复', '2', '2', '1', '1', '1564924337');
INSERT INTO `law_help` VALUES ('53', '6', '20', '1', '6', 'sam的回复sam的回复sam的回复sam的回复sam的回复sam的回复sam的回复sam的回复sam的回复', '2', '2', '1', '1', '1564924403');
INSERT INTO `law_help` VALUES ('54', '3', '12', '1', '3', '小明2的回复', '2', '2', '1', '1', '1564924622');
INSERT INTO `law_help` VALUES ('55', '6', '21', '1', '6', 'Sam的回复', '2', '2', '1', '1', '1564924638');
INSERT INTO `law_help` VALUES ('56', '7', '22', '1', '7', '小明4的回复小明4的回复小明4的回复小明4的回复小明4的回复小明4的回复小明4的回复', '2', '2', '1', '1', '1564924664');
INSERT INTO `law_help` VALUES ('57', '8', '24', '1', '8', '小明5的回复小明5的回复小明5的回复小明5的回复小明5的回复小明5的回复', '2', '2', '1', '1', '1564924723');
INSERT INTO `law_help` VALUES ('58', '1', '0', '1', '0', '第一次司法救助', '1', '2', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('59', '1', '0', '1', '0', '第一次司法救助', '1', '2', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('60', '2', '0', '2', '0', '第一次司法救助', '1', '2', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('61', '2', '0', '2', '0', '第一次司法救助', '1', '2', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('62', '3', '0', '3', '0', '第一次司法救助', '1', '1', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('63', '3', '0', '3', '0', '第一次司法救助', '1', '1', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('64', '4', '0', '4', '0', '第一次司法救助', '1', '1', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('65', '4', '0', '4', '0', '第一次司法救助', '1', '1', '1', '3', '1564466755');
INSERT INTO `law_help` VALUES ('66', '1', '58', '1', '1', '小明1的司法救助回复小明1的司法救助回复小明1的司法救助回复小明1的司法救助回复小明1的司法救助回复小明1的司法救助回复', '2', '2', '1', '3', '1564925072');
INSERT INTO `law_help` VALUES ('67', '2', '60', '1', '2', 'Tom的司法救助回复Tom的司法救助回复Tom的司法救助回复Tom的司法救助回复Tom的司法救助回复Tom的司法救助回复Tom的司法救助回复', '2', '2', '1', '3', '1564925128');
