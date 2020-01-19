/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : court

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-08-21 12:32:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `news_type_id` int(11) NOT NULL DEFAULT '1' COMMENT '新闻对应的类型',
  `title` varchar(500) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '新闻标题',
  `brief` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '新闻内容的摘要',
  `read` int(11) NOT NULL DEFAULT '0' COMMENT '阅读人数',
  `like` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `author` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '发布新闻的作者',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:数据正常，2：数据异常',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '新闻创建的时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COMMENT='新闻';

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '1', '四个到位 习近平对主题教育提出了新要求s', '习近平强调，各地区各部门要注意抓\"四个到位\":思想认识到位、检视问题到位、整改落实到位、组织领导到位。', '3', '4', 'Boom', '2', '0');
INSERT INTO `news` VALUES ('2', '2', '李克强电贺冯德莱恩当选欧盟委员会主席', '国务院总理李克强18日致电乌尔苏拉·冯德莱恩，祝贺她当选新一届欧盟委员会主席。', '0', '0', 'Boom', '2', '0');
INSERT INTO `news` VALUES ('3', '2', '上半年经济表现如何？三个\"关键词\"划重点', '国家统计局近日发布上半年宏观经济数据，显示出中国经济总体平稳、经济高质量发展取得新进展的良好态势。', '0', '0', 'Boom', '2', '0');
INSERT INTO `news` VALUES ('4', '1', '习近平内蒙古考察调研精彩瞬间全记录', '7月15日至16日，中共中央总书记、国家主席、中央军委主席习近平在内蒙古考察并指导开展“不忘初心、牢记使命”主题教育。15日，习近平在赤峰市松山区兴安街道', '0', '0', 'Boom', '2', '0');
INSERT INTO `news` VALUES ('5', '3', '杭州留泗路边的行道树怎么上了“紧箍咒”？高温天来了，葱葱茏茏的行道树算得上是行人最大的遮阳伞了，匆忙赶路时往树荫下一走，顿时解暑不少。不过最近，通过留泗路来往叶埠桥和龙坞九街的市民却发现，他们的“遮阳伞”受伤了。家住杭州西湖区转塘街道叶埠桥社区的孙先生很气愤，留泗路散步时，路边的行道树给他遮了不少阴，但是最近行道树看起来不太对劲，仔细一看才知道，竟然是树木支撑架的铁丝勒进了树皮里。“树是会长大的，好好的树看着也不会倒，为什么要勒这么紧，看着都难受。”好好的行道树 谁给上了紧箍咒呢？声明：转载此文是出于传递更多信息之目的。若有来源标注错误或侵犯了您的合法权益，请作者持权属证明与本网联系，我们将及时更正、删除，谢谢。\r\n杭州留泗路边的行道树怎么上了“紧箍咒”？', '高温天来了，葱葱茏茏的行道树算得上是行人最大的遮阳伞了，匆忙赶路时往树荫下一走，顿时解暑不少。不过最近，通过留泗路来往叶埠桥和龙坞九街的市民却发现，他们的“遮阳伞”受伤了。家住杭州西湖区转塘街道叶埠桥社区的孙先生很气愤，留泗路散步时，路边的行道树给他遮了不少阴，但是最近行道树看起来不太对劲，仔细一看才知道，竟然是树木支撑架的铁丝勒进了树皮里。“树是会长大的，好好的树看着也不会倒，为什么要勒这么紧，看着都难受。”好好的行道树 谁给上了紧箍咒呢？声明：转载此文是出于传递更多信息之目的。若有来源标注错误或侵犯了您的合法权益，请作者持权属证明与本网联系，我们将及时更正、删除，谢谢。', '0', '0', 'Boom', '1', '0');
INSERT INTO `news` VALUES ('6', '1', '黄河干流水量暴涨 壶口瀑布波涛汹涌', '黄河干流水量暴涨 壶口瀑布波涛汹涌', '0', '0', 'Boom', '1', '0');
INSERT INTO `news` VALUES ('7', '1', '习近平会见驻外使节工作会议与会使节', '习近平会见2019年度驻外使节工作会议与会使节', '0', '0', 'Boom', '1', '0');
INSERT INTO `news` VALUES ('8', '4', '云南民众欢度“花脸节”', '云南民众欢度“花脸节” 互相“抹黑”送祝福', '0', '0', 'Boom', '2', '0');
INSERT INTO `news` VALUES ('9', '1', '云南昆明举行航空军事夏令营', '云南昆明举行航空军事夏令营', '0', '0', 'Boom', '2', '0');
INSERT INTO `news` VALUES ('10', '1', '现闯红灯要当场考试 达到80分才能走人', '现闯红灯要当场考试 达到80分才能走人', '0', '0', 'Boom', '1', '0');
INSERT INTO `news` VALUES ('11', '5', '航拍壮观万人龙虾宴', '航拍壮观万人龙虾宴 重达一吨“大龙虾”色香味俱全', '0', '0', 'Boom', '1', '0');
INSERT INTO `news` VALUES ('12', '1', '牙齿正畸，让你的牙齿绝不出轨！', '牙齿矫正的方法较多，根据实际情况选用，对于未成年人矫正牙齿，尽量选择矫正器的方式加以矫正牙齿，使牙颌畸形恢复正常或接近正常水平。', '0', '0', 'Boom', '1', '0');
INSERT INTO `news` VALUES ('59', '1', '牙齿正畸，让你的牙齿绝不出轨！s', '牙齿矫正的方法较多，根据实际情况选用，对于未成年人矫正牙齿，尽量选择矫正器的方式加以矫正牙齿，使牙颌畸形恢复正常或接近正常水平。 0 0 Boom 0 12到第 2 页确定共 12 条 牙齿矫正的方法较多，根据实际情况选用，对于未成年人矫正牙齿，尽量选择矫正器的方式加以矫正牙齿，使牙颌畸形恢复正常或接近正常水平。s', '1', '0', 'Boom', '1', '1563857226');
INSERT INTO `news` VALUES ('60', '1', '牙齿正畸，让你的牙齿绝不出轨！s', '牙齿矫正的方法较多，根据实际情况选用，对于未成年人矫正牙齿，尽量选择矫正器的方式加以矫正牙齿，使牙颌畸形恢复正常或接近正常水平。 0 0 Boom 0 12到第 2 页确定共 12 条 牙齿矫正的方法较多，根据实际情况选用，对于未成年人矫正牙齿，尽量选择矫正器的方式加以矫正牙齿，使牙颌畸形恢复正常或接近正常水平。s', '8', '2', 'Boom', '1', '1563857352');
INSERT INTO `news` VALUES ('67', '1', 'ni', 'n', '0', '0', 'QEcode1', '1', '1565315686');
INSERT INTO `news` VALUES ('66', '1', 's', 's', '0', '0', 'QEcode1', '1', '1565071887');
INSERT INTO `news` VALUES ('64', '1', 's', 's', '0', '0', 'Boom', '2', '1564111844');
INSERT INTO `news` VALUES ('65', '1', 's', 's', '0', '0', 'QEcode1', '2', '1564113468');
INSERT INTO `news` VALUES ('68', '1', '是', '是', '0', '0', 'QEcode1', '1', '1565335105');
INSERT INTO `news` VALUES ('69', '1', 'a', 'a', '0', '0', 'QEcode1', '1', '1566297265');
INSERT INTO `news` VALUES ('70', '2', 'aa', 'aa', '0', '0', 'QEcode1', '2', '1566297423');
