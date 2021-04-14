/*
 Navicat Premium Data Transfer

 Source Server         : 234
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 14/04/2021 16:52:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleHeadPic` varchar(255) DEFAULT NULL COMMENT '文章封面',
  `articleName` varchar(255) DEFAULT NULL COMMENT '文章名称',
  `articleTag` varchar(255) DEFAULT NULL COMMENT '文章标签',
  `articleRemark` varchar(255) DEFAULT NULL COMMENT '文章简介',
  `articleReadCount` int(11) DEFAULT '0' COMMENT '文章阅读量',
  `articleState` int(11) DEFAULT '0' COMMENT '文章状态',
  `managerId` int(11) DEFAULT NULL,
  `managerName` varchar(50) DEFAULT NULL COMMENT '作者名称',
  `articleContent` text COMMENT '文章内容',
  `createTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `articleType` int(2) DEFAULT NULL COMMENT '文章类型',
  `articleStarNum` int(11) DEFAULT NULL COMMENT '文章点赞数',
  `articleConNum` int(11) DEFAULT NULL COMMENT '文章评论数',
  `enclosure` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ARTICLE_NAME` (`articleName`) USING BTREE COMMENT '文章名称索引'
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
BEGIN;
INSERT INTO `blog_article` VALUES (37, NULL, '123123', NULL, '2134124', 1, 1, NULL, 'admin', '123123', '2021-04-14 14:29:57', 1001, NULL, NULL, NULL);
INSERT INTO `blog_article` VALUES (38, NULL, '23456345', '34534,', 'rtrdtrg', 0, 0, 1, 'admin', '<h2><a id=\"3456_0\"></a>3456</h2>\n', '2021-04-14 14:30:15', 1002, NULL, NULL, NULL);
INSERT INTO `blog_article` VALUES (39, NULL, '1003', '1003,', '10031003', 0, 0, 1, 'admin', '<p>1003</p>\n', '2021-04-14 14:35:36', 1003, NULL, NULL, NULL);
INSERT INTO `blog_article` VALUES (40, NULL, 'asdfasdf', 'asdf,', '234234', 12, 0, 1, 'admin', '<p>234123452345</p>\n', '2021-04-14 14:40:39', 1001, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
BEGIN;
INSERT INTO `blog_category` VALUES (1001, '技术');
INSERT INTO `blog_category` VALUES (1002, '源码');
INSERT INTO `blog_category` VALUES (1003, '程序');
COMMIT;

-- ----------------------------
-- Table structure for blog_code
-- ----------------------------
DROP TABLE IF EXISTS `blog_code`;
CREATE TABLE `blog_code` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `vaule` varchar(50) DEFAULT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `status` int(2) DEFAULT '1' COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) DEFAULT NULL COMMENT '文章id',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `createTime` varchar(255) DEFAULT NULL COMMENT '评论时间',
  `byManagerId` int(11) DEFAULT NULL COMMENT '评论人',
  `pid` int(11) DEFAULT NULL COMMENT '被评论人',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `articleName` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
BEGIN;
INSERT INTO `blog_comment` VALUES (1, 40, 'asdfasdfasd', '2021-04-14 16:33:47', NULL, NULL, '', 'asdfasdf');
INSERT INTO `blog_comment` VALUES (2, 40, 'asdfasdfasdf ', '2021-04-14 16:33:57', NULL, 1, '', 'asdfasdf');
INSERT INTO `blog_comment` VALUES (3, 40, 'asdfasdf ', '2021-04-14 16:34:04', NULL, 2, '', 'asdfasdf');
INSERT INTO `blog_comment` VALUES (4, 40, 'asdfasdfasdf', '2021-04-14 16:34:13', NULL, 3, '', 'asdfasdf');
INSERT INTO `blog_comment` VALUES (5, 40, 'asdfasdf', '2021-04-14 16:34:19', NULL, NULL, '', 'asdfasdf');
INSERT INTO `blog_comment` VALUES (6, 40, 'litian@alksdfjklasdf', '2021-04-14 16:34:31', NULL, NULL, '', 'asdfasdf');
INSERT INTO `blog_comment` VALUES (7, 40, '1234', '2021-04-14 16:50:59', 2, NULL, '123', 'asdfasdf');
COMMIT;

-- ----------------------------
-- Table structure for blog_friend_address
-- ----------------------------
DROP TABLE IF EXISTS `blog_friend_address`;
CREATE TABLE `blog_friend_address` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '友链名称',
  `address` varchar(500) DEFAULT NULL COMMENT '友链地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_friend_address
-- ----------------------------
BEGIN;
INSERT INTO `blog_friend_address` VALUES (1, '伊成', 'http://www.devcheng.net/');
INSERT INTO `blog_friend_address` VALUES (2, '筱进GG', 'http://www.cicoding.cn/');
INSERT INTO `blog_friend_address` VALUES (3, '理想乡', 'https://www.yanghainan.top');
INSERT INTO `blog_friend_address` VALUES (4, '空梦', 'http://youke0.xyz');
INSERT INTO `blog_friend_address` VALUES (5, '凯奇', 'https://www.sjl511.top/');
INSERT INTO `blog_friend_address` VALUES (6, '小谢', 'https://Atjava.github.io ');
COMMIT;

-- ----------------------------
-- Table structure for blog_ip_address
-- ----------------------------
DROP TABLE IF EXISTS `blog_ip_address`;
CREATE TABLE `blog_ip_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `managerId` int(11) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL COMMENT '访问ip',
  `status` int(2) DEFAULT '1' COMMENT '状态',
  `loginTime` varchar(50) DEFAULT NULL COMMENT '访问时间',
  `managerName` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_ip_address
-- ----------------------------
BEGIN;
INSERT INTO `blog_ip_address` VALUES (1, 1, '0:0:0:0:0:0:0:1', 1, '2021-04-14 16:26:21', '');
INSERT INTO `blog_ip_address` VALUES (2, 1, '36.106.85.51', 1, '2019-11-07 14:24:17', '');
INSERT INTO `blog_ip_address` VALUES (3, 1, '192.168.1.188', 1, '2019-11-07 14:19:55', '');
INSERT INTO `blog_ip_address` VALUES (4, 1, '127.0.0.1', 1, '2021-01-23 21:23:57', '');
INSERT INTO `blog_ip_address` VALUES (5, 3, '0:0:0:0:0:0:0:1', 1, '2021-01-21 22:59:28', '');
INSERT INTO `blog_ip_address` VALUES (6, 5, '0:0:0:0:0:0:0:1', 1, '2021-02-06 11:05:09', '');
INSERT INTO `blog_ip_address` VALUES (7, 2, '0:0:0:0:0:0:0:1', 1, '2021-04-14 16:50:27', '');
COMMIT;

-- ----------------------------
-- Table structure for blog_manager
-- ----------------------------
DROP TABLE IF EXISTS `blog_manager`;
CREATE TABLE `blog_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `agipassword` varchar(255) DEFAULT NULL COMMENT '临时密码',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `headPic` varchar(255) DEFAULT NULL COMMENT '头像',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(2) DEFAULT '1' COMMENT '状态',
  `type` varchar(255) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_manager
-- ----------------------------
BEGIN;
INSERT INTO `blog_manager` VALUES (1, 'admin', 'admin', NULL, '21232f297a57a5a743894a0e4a801fc3', '1.png', '2019-11-12 10:33:47', 1, '超级管理员');
INSERT INTO `blog_manager` VALUES (2, '123', '1234', NULL, '289dff07669d7a23de0ef88d2f7129e7', NULL, '2021-04-14 16:50:10', 1, '博主');
COMMIT;

-- ----------------------------
-- Table structure for blog_music
-- ----------------------------
DROP TABLE IF EXISTS `blog_music`;
CREATE TABLE `blog_music` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '音乐名称',
  `artist` varchar(50) DEFAULT NULL COMMENT '作者',
  `url` varchar(255) DEFAULT NULL COMMENT '歌曲来源',
  `cover` varchar(255) DEFAULT NULL COMMENT '歌曲封面',
  `lrc` varchar(8000) DEFAULT NULL COMMENT '歌词',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_music
-- ----------------------------
BEGIN;
INSERT INTO `blog_music` VALUES (1, '听妈妈的话', '周杰伦', 'http://www.170mv.com/kw/antiserver.kuwo.cn/anti.s?rid=MUSIC_138243&response=res&format=mp3|aac&type=convert_url&br=128kmp3&agent=iPhone&callback=getlink&jpcallback=getlink.mp3', 'http://img1.kuwo.cn/star/starheads/240/42/97/3914752958.jpg', '[00:11.64]小朋友 你是否有很多问号 \\n[00:14.26]为什么 别人在那看漫画 \\n[00:16.89]我却在学画画 对这钢琴说话 \\n[00:19.37]别人在玩游戏 我却靠在墙壁背我的ABC \\n[00:22.38]我说我要一架大大的飞机 \\n[00:24.91]我却得到一只旧旧螺旋机 \\n[00:27.48]为什么要听妈妈的话 \\n[00:29.79]长大后你就会开始懂得这段话 哼 \\n[00:32.26]长大后我开始明白 \\n[00:35.23]为什么我 跑得比别人快 \\n[00:36.64]飞得比别人高 \\n[00:37.88]将来大家看的都是我画的漫画 \\n[00:40.23]大家唱的都是 我写的歌 \\n[00:42.95]妈妈的辛苦 不让你看见 \\n[00:45.57]温暖的事都在她心里面 \\n[00:48.01]有空就得多摸摸她的手 \\n[00:50.70]把手牵着一起梦游 \\n[00:53.10]听妈妈的话 别让她受伤 \\n[01:03.14]想快快长大 才能保护她 \\n[01:12.93]美丽的白发 幸福中发芽 \\n[01:23.96]天使的魔法 温暖中慈祥 \\n[01:34.43]在你的未来 音乐是你的王牌 \\n[01:36.21]那王牌谈的恋爱 \\n[01:37.68]而我不想把你教坏 \\n[01:39.21]还是听妈妈的话吧 \\n[01:40.52]晚点在恋爱吧 \\n[01:41.96]我知道你未来的路 \\n[01:44.09]干嘛比我更清楚 \\n[01:45.40]你因为太多学习的同学 \\n[01:46.53]在这块写东写西 \\n[01:47.50]但我建议最好听妈妈 \\n[01:49.10]我会用功读书 \\n[01:50.23]用功读书怎么会从我嘴巴说出 \\n[01:52.34]不想你输所以要叫你用功读书 \\n[01:55.47]妈妈挑给你的毛病你要好好的收着 \\n[01:57.56]因为不知道是我要告诉她我还留着 \\n[01:59.91]对了 我会遇到了周润发 \\n[02:02.31]所以你可以跟同学炫耀 \\n[02:03.38]赌神未来是你爸爸 \\n[02:04.47]我找不到你写的情书 \\n[02:06.94]你喜欢的要承认 \\n[02:08.02]因为我会了解你会在操场上牵她 \\n[02:09.40]你会开始喜欢唱流行歌 \\n[02:12.81]因为张学友开始准备唱吻别 \\n[02:15.43]听妈妈的话 别让她受伤 \\n[02:26.22]想快快长大 才能保护她 \\n[02:36.16]美丽的白发 幸福中发芽 \\n[02:46.66]天使的魔法 温暖中慈祥 \\n[02:56.68]听妈妈的话 别让她受伤 \\n[03:07.46]想快快长大 才能保护她 \\n[03:17.27]长大后我开始明白 为什么我 \\n[03:20.81]跑得比别人快 飞得比别人高 \\n[03:22.56]将来大家看的都是我画的漫画 \\n[03:25.52]大家唱的都是我写的歌 \\n[03:28.31]妈妈的辛苦她不让你看见 \\n[03:30.72]温暖的事都在她心里面 \\n[03:33.31]有空就得多摸摸她的手 \\n[03:36.15]把手牵着一起梦游 \\n[03:38.34]听妈妈的话 别让她受伤 \\n[03:48.72]想快快长大 才能保护她 \\n[03:59.10]美丽的白发 幸福中发芽 \\n[04:09.32]天使的魔法 温暖中慈祥');
INSERT INTO `blog_music` VALUES (2, '以父之名', '周杰伦', 'http://www.170mv.com/kw/antiserver.kuwo.cn/anti.s?rid=MUSIC_28386348&response=res&format=mp3|aac&type=convert_url&br=128kmp3&agent=iPhone&callback=getlink&jpcallback=getlink.mp3', 'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=ce3122aecb8065387beaa315afe6c679/d01373f082025aaff92dd92bfaedab64034f1a36.jpg', '\\n[00:18.80]以父之名 \\n[00:35.80]周杰伦 \\n[00:52.80]制作人：秋晓之 QQ：285552115 \\n[01:09.80] \\n[01:26.56]微凉的晨露沾湿黑礼服 \\n[01:29.67]石板路有雾父在低诉 \\n[01:32.52]无奈的觉悟只能更残酷 \\n[01:35.33]一切都为了通往圣堂的路 \\n[01:38.02]吹不散的雾隐没了意图 \\n[01:40.67]谁轻柔踱步停住 \\n[01:43.34]还来不及哭穿过的子弹就带走温度 \\n[01:47.15] \\n[01:48.69]我们每个人都有罪 \\n[01:50.44]犯着不同的罪 \\n[01:51.77]我能决定谁对 \\n[01:53.15]谁又该要沉睡 \\n[01:54.53]争论不能解决 \\n[01:55.76]在永无止境的夜 \\n[01:57.35]关掉你的嘴 \\n[01:58.69]唯一的恩惠 \\n[01:59.88]挡在前面的人都有罪 \\n[02:01.18]后悔也无路可退 \\n[02:02.64]以父之名判决 \\n[02:03.96]那感觉没有适合词汇 \\n[02:05.59]就像边笑边掉泪 \\n[02:07.04]凝视着完全的黑 \\n[02:08.42]阻挡悲剧蔓延的悲剧会让我沉醉 \\n[02:10.72]低头亲吻我的左手 \\n[02:12.17]换取被宽恕的承诺 \\n[02:13.50]老旧管风琴在角落 \\n[02:14.93]一直一直一直伴奏 \\n[02:16.25]黑色帘幕被风吹动 \\n[02:17.66]阳光无言地穿透 \\n[02:19.18]洒向那群被我驯服后的 兽 \\n[02:21.88]沉默地喊叫沉默地喊叫 \\n[02:23.55]孤单开始发酵 \\n[02:24.88]不停对着我嘲笑 \\n[02:26.29]回忆逐渐燃烧 \\n[02:27.66]曾经纯真的画面 \\n[02:28.77]残忍地温柔出现 \\n[02:30.21]脆弱时间到 \\n[02:31.33]我们一起来祷告 \\n[02:32.95]仁慈的父我已坠入 \\n[02:35.73]看不见罪的国度 \\n[02:38.77]请原谅我的自负 \\n[02:41.68] \\n[02:43.77]没人能说没人可说 \\n[02:46.59]好难承受 \\n[02:48.67]荣耀的背后刻着一道孤独 \\n[02:52.52] \\n[02:54.66]闭上双眼我又看见 \\n[02:57.67]当年那梦的画面 \\n[03:00.59]天空是蒙蒙的雾 \\n[03:03.56] \\n[03:05.76]父亲牵着我的双手 \\n[03:08.66]轻轻走过 \\n[03:10.67]清晨那安安静静的石板路 \\n[03:14.58] \\n[03:46.34]低头亲吻我的左手 \\n[03:47.88]换取被宽恕的承诺 \\n[03:49.21]老旧管风琴在角落 \\n[03:50.68]一直一直一直伴奏 \\n[03:52.21]黑色帘幕被风吹动 \\n[03:53.55]阳光无言地穿透 \\n[03:54.88]洒向那群被我驯服后的 兽 \\n[03:57.56]沉默地喊叫沉默地喊叫 \\n[03:59.21]孤单开始发酵 \\n[04:00.68]不停对着我嘲笑 \\n[04:01.77]回忆逐渐燃烧 \\n[04:03.33]曾经纯真的画面 \\n[04:04.54]残忍地温柔出现 \\n[04:05.69]脆弱时间到 \\n[04:06.88]我们一起来祷告 \\n[04:08.65]仁慈的父我已坠入 \\n[04:11.90]看不见罪的国度 \\n[04:14.58]请原谅我的自负 \\n[04:17.97] \\n[04:19.88]没人能说没人可说 \\n[04:22.85]好难承受 \\n[04:24.66]荣耀的背后刻着一道孤独 \\n[04:27.67] \\n[04:30.84]仁慈的父我已坠入 \\n[04:36.44]看不见罪的国度 \\n[04:42.07]请原谅我 我的自负 \\n[04:47.36]刻着一道孤独 \\n[04:52.74]仁慈的父我已坠入 \\n[04:55.56]看不见罪的国度 \\n[04:58.28]请原谅我的自负 \\n[05:01.75] \\n[05:03.58]没人能说没人可说 \\n[05:06.49]好难承受 \\n[05:08.66]荣耀的背后刻着一道孤独 \\n[05:12.29] \\n[05:14.32]那斑驳的家徽 我擦拭了一夜(闭上双眼我又看见) \\n[05:17.45]孤独的光辉 我才懂的感觉(当年那梦的画面) \\n[05:20.13]烛光 不 不 停的 摇晃(天空是蒙蒙的雾) \\n[05:22.41]猫头鹰在 窗棂上 对著远方眺望 \\n[05:25.50]通向 大厅的长廊 (父亲牵着我的双手) \\n[05:28.42]一样 说不出的沧桑(轻轻走过)');
INSERT INTO `blog_music` VALUES (3, 'Wayward One', 'Jillian Rae', 'https://www.170mv.com/kw/antiserver.kuwo.cn/anti.s?rid=MUSIC_72217588&response=res&format=mp3|aac&type=convert_url&br=128kmp3&agent=iPhone&callback=getlink&jpcallback=getlink.mp3', 'https://p1.music.126.net/K0-IPcIQ9QFvA0jXTBqoWQ==/109951163636756693.jpg?param=300y300', '暂无歌词');
INSERT INTO `blog_music` VALUES (6, '听妈妈的话', '周杰伦', 'http://www.170hi.com/kw/antiserver.kuwo.cn/anti.s?rid=MUSIC_6871864&response=res&format=mp3|aac&type=convert_url&br=128kmp3&agent=iPhone&callback=getlink&jpcallback=getlink.mp3', 'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=ce3122aecb8065387beaa315afe6c679/d01373f082025aaff92dd92bfaedab64034f1a36.jpg', '[00:11.64]小朋友 你是否有很多问号 \\n[00:14.26]为什么 别人在那看漫画 \\n[00:16.89]我却在学画画 对这钢琴说话 \\n[00:19.37]别人在玩游戏 我却靠在墙壁背我的ABC \\n[00:22.38]我说我要一架大大的飞机 \\n[00:24.91]我却得到一只旧旧螺旋机 \\n[00:27.48]为什么要听妈妈的话 \\n[00:29.79]长大后你就会开始懂得这段话 哼 \\n[00:32.26]长大后我开始明白 \\n[00:35.23]为什么我 跑得比别人快 \\n[00:36.64]飞得比别人高 \\n[00:37.88]将来大家看的都是我画的漫画 \\n[00:40.23]大家唱的都是 我写的歌 \\n[00:42.95]妈妈的辛苦 不让你看见 \\n[00:45.57]温暖的事都在她心里面 \\n[00:48.01]有空就得多摸摸她的手 \\n[00:50.70]把手牵着一起梦游 \\n[00:53.10]听妈妈的话 别让她受伤 \\n[01:03.14]想快快长大 才能保护她 \\n[01:12.93]美丽的白发 幸福中发芽 \\n[01:23.96]天使的魔法 温暖中慈祥 \\n[01:34.43]在你的未来 音乐是你的王牌 \\n[01:36.21]那王牌谈的恋爱 \\n[01:37.68]而我不想把你教坏 \\n[01:39.21]还是听妈妈的话吧 \\n[01:40.52]晚点在恋爱吧 \\n[01:41.96]我知道你未来的路 \\n[01:44.09]干嘛比我更清楚 \\n[01:45.40]你因为太多学习的同学 \\n[01:46.53]在这块写东写西 \\n[01:47.50]但我建议最好听妈妈 \\n[01:49.10]我会用功读书 \\n[01:50.23]用功读书怎么会从我嘴巴说出 \\n[01:52.34]不想你输所以要叫你用功读书 \\n[01:55.47]妈妈挑给你的毛病你要好好的收着 \\n[01:57.56]因为不知道是我要告诉她我还留着 \\n[01:59.91]对了 我会遇到了周润发 \\n[02:02.31]所以你可以跟同学炫耀 \\n[02:03.38]赌神未来是你爸爸 \\n[02:04.47]我找不到你写的情书 \\n[02:06.94]你喜欢的要承认 \\n[02:08.02]因为我会了解你会在操场上牵她 \\n[02:09.40]你会开始喜欢唱流行歌 \\n[02:12.81]因为张学友开始准备唱吻别 \\n[02:15.43]听妈妈的话 别让她受伤 \\n[02:26.22]想快快长大 才能保护她 \\n[02:36.16]美丽的白发 幸福中发芽 \\n[02:46.66]天使的魔法 温暖中慈祥 \\n[02:56.68]听妈妈的话 别让她受伤 \\n[03:07.46]想快快长大 才能保护她 \\n[03:17.27]长大后我开始明白 为什么我 \\n[03:20.81]跑得比别人快 飞得比别人高 \\n[03:22.56]将来大家看的都是我画的漫画 \\n[03:25.52]大家唱的都是我写的歌 \\n[03:28.31]妈妈的辛苦她不让你看见 \\n[03:30.72]温暖的事都在她心里面 \\n[03:33.31]有空就得多摸摸她的手 \\n[03:36.15]把手牵着一起梦游 \\n[03:38.34]听妈妈的话 别让她受伤 \\n[03:48.72]想快快长大 才能保护她 \\n[03:59.10]美丽的白发 幸福中发芽 \\n[04:09.32]天使的魔法 温暖中慈祥');
INSERT INTO `blog_music` VALUES (7, '以父之名', '周杰伦', 'http://www.170mv.com/kw/antiserver.kuwo.cn/anti.s?rid=MUSIC_28386348&response=res&format=mp3|aac&type=convert_url&br=128kmp3&agent=iPhone&callback=getlink&jpcallback=getlink.mp3', 'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=ce3122aecb8065387beaa315afe6c679/d01373f082025aaff92dd92bfaedab64034f1a36.jpg', '\\n[00:18.80]以父之名 \\n[00:35.80]周杰伦 \\n[00:52.80]制作人：秋晓之 QQ：285552115 \\n[01:09.80] \\n[01:26.56]微凉的晨露沾湿黑礼服 \\n[01:29.67]石板路有雾父在低诉 \\n[01:32.52]无奈的觉悟只能更残酷 \\n[01:35.33]一切都为了通往圣堂的路 \\n[01:38.02]吹不散的雾隐没了意图 \\n[01:40.67]谁轻柔踱步停住 \\n[01:43.34]还来不及哭穿过的子弹就带走温度 \\n[01:47.15] \\n[01:48.69]我们每个人都有罪 \\n[01:50.44]犯着不同的罪 \\n[01:51.77]我能决定谁对 \\n[01:53.15]谁又该要沉睡 \\n[01:54.53]争论不能解决 \\n[01:55.76]在永无止境的夜 \\n[01:57.35]关掉你的嘴 \\n[01:58.69]唯一的恩惠 \\n[01:59.88]挡在前面的人都有罪 \\n[02:01.18]后悔也无路可退 \\n[02:02.64]以父之名判决 \\n[02:03.96]那感觉没有适合词汇 \\n[02:05.59]就像边笑边掉泪 \\n[02:07.04]凝视着完全的黑 \\n[02:08.42]阻挡悲剧蔓延的悲剧会让我沉醉 \\n[02:10.72]低头亲吻我的左手 \\n[02:12.17]换取被宽恕的承诺 \\n[02:13.50]老旧管风琴在角落 \\n[02:14.93]一直一直一直伴奏 \\n[02:16.25]黑色帘幕被风吹动 \\n[02:17.66]阳光无言地穿透 \\n[02:19.18]洒向那群被我驯服后的 兽 \\n[02:21.88]沉默地喊叫沉默地喊叫 \\n[02:23.55]孤单开始发酵 \\n[02:24.88]不停对着我嘲笑 \\n[02:26.29]回忆逐渐燃烧 \\n[02:27.66]曾经纯真的画面 \\n[02:28.77]残忍地温柔出现 \\n[02:30.21]脆弱时间到 \\n[02:31.33]我们一起来祷告 \\n[02:32.95]仁慈的父我已坠入 \\n[02:35.73]看不见罪的国度 \\n[02:38.77]请原谅我的自负 \\n[02:41.68] \\n[02:43.77]没人能说没人可说 \\n[02:46.59]好难承受 \\n[02:48.67]荣耀的背后刻着一道孤独 \\n[02:52.52] \\n[02:54.66]闭上双眼我又看见 \\n[02:57.67]当年那梦的画面 \\n[03:00.59]天空是蒙蒙的雾 \\n[03:03.56] \\n[03:05.76]父亲牵着我的双手 \\n[03:08.66]轻轻走过 \\n[03:10.67]清晨那安安静静的石板路 \\n[03:14.58] \\n[03:46.34]低头亲吻我的左手 \\n[03:47.88]换取被宽恕的承诺 \\n[03:49.21]老旧管风琴在角落 \\n[03:50.68]一直一直一直伴奏 \\n[03:52.21]黑色帘幕被风吹动 \\n[03:53.55]阳光无言地穿透 \\n[03:54.88]洒向那群被我驯服后的 兽 \\n[03:57.56]沉默地喊叫沉默地喊叫 \\n[03:59.21]孤单开始发酵 \\n[04:00.68]不停对着我嘲笑 \\n[04:01.77]回忆逐渐燃烧 \\n[04:03.33]曾经纯真的画面 \\n[04:04.54]残忍地温柔出现 \\n[04:05.69]脆弱时间到 \\n[04:06.88]我们一起来祷告 \\n[04:08.65]仁慈的父我已坠入 \\n[04:11.90]看不见罪的国度 \\n[04:14.58]请原谅我的自负 \\n[04:17.97] \\n[04:19.88]没人能说没人可说 \\n[04:22.85]好难承受 \\n[04:24.66]荣耀的背后刻着一道孤独 \\n[04:27.67] \\n[04:30.84]仁慈的父我已坠入 \\n[04:36.44]看不见罪的国度 \\n[04:42.07]请原谅我 我的自负 \\n[04:47.36]刻着一道孤独 \\n[04:52.74]仁慈的父我已坠入 \\n[04:55.56]看不见罪的国度 \\n[04:58.28]请原谅我的自负 \\n[05:01.75] \\n[05:03.58]没人能说没人可说 \\n[05:06.49]好难承受 \\n[05:08.66]荣耀的背后刻着一道孤独 \\n[05:12.29] \\n[05:14.32]那斑驳的家徽 我擦拭了一夜(闭上双眼我又看见) \\n[05:17.45]孤独的光辉 我才懂的感觉(当年那梦的画面) \\n[05:20.13]烛光 不 不 停的 摇晃(天空是蒙蒙的雾) \\n[05:22.41]猫头鹰在 窗棂上 对著远方眺望 \\n[05:25.50]通向 大厅的长廊 (父亲牵着我的双手) \\n[05:28.42]一样 说不出的沧桑(轻轻走过)');
COMMIT;

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_test
-- ----------------------------
BEGIN;
INSERT INTO `t_test` VALUES (1, '张三');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
