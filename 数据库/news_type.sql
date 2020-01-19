/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : court

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-08-21 12:32:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news_type`
-- ----------------------------
DROP TABLE IF EXISTS `news_type`;
CREATE TABLE `news_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '新闻类型id',
  `title` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '新闻类型标题',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:数据正常，2：数据异常',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间，时间戳格式',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='新闻类型';

-- ----------------------------
-- Records of news_type
-- ----------------------------
INSERT INTO `news_type` VALUES ('1', '新闻分类1', '2', '1562921980');
INSERT INTO `news_type` VALUES ('2', '新闻分类2', '1', '1562921980');
INSERT INTO `news_type` VALUES ('3', '新闻分类3', '2', '1562921980');
INSERT INTO `news_type` VALUES ('4', '新闻分类4', '1', '1562921980');
INSERT INTO `news_type` VALUES ('5', '新闻分类5', '2', '1562921980');
INSERT INTO `news_type` VALUES ('8', '新闻类型6', '1', '1566361533');
