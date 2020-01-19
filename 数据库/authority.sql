/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : court

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-08-05 13:00:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:数据状态正常，2：异常',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间，时间戳格式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '申诉查阅', 'appeal_view', '1', '0');
INSERT INTO `authority` VALUES ('2', '申诉下载', 'appeal_download', '1', '0');
INSERT INTO `authority` VALUES ('3', '举报查阅', 'inform_view', '1', '0');
INSERT INTO `authority` VALUES ('4', '举报下载', 'inform_download', '1', '0');
INSERT INTO `authority` VALUES ('5', '控告查阅', 'sue_view', '1', '0');
INSERT INTO `authority` VALUES ('6', '控告下载', 'sue_download', '1', '0');
INSERT INTO `authority` VALUES ('9', '军检类型', 'appeal_type', '1', '0');
INSERT INTO `authority` VALUES ('10', '新闻管理', 'news', '1', '0');
INSERT INTO `authority` VALUES ('11', '法务列表', 'law_library', '1', '0');
INSERT INTO `authority` VALUES ('12', '法务类型', 'law_type', '1', '0');
INSERT INTO `authority` VALUES ('13', '法律咨询', 'law_help1', '1', '0');
INSERT INTO `authority` VALUES ('14', '法律援助', 'law_help2', '1', '0');
INSERT INTO `authority` VALUES ('15', '司法求助', 'law_help3', '1', '0');
INSERT INTO `authority` VALUES ('16', '用户列表', 'user_deal', '1', '0');
INSERT INTO `authority` VALUES ('17', '管理员列表', 'admin_deal', '1', '0');
