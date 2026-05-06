/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80011 (8.0.11)
 Source Host           : localhost:3306
 Source Schema         : cl636300194

 Target Server Type    : MySQL
 Target Server Version : 80011 (8.0.11)
 File Encoding         : 65001

 Date: 31/03/2026 14:50:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


CREATE DATABASE IF NOT EXISTS cl636300194 DEFAULT CHARACTER SET utf8;
USE cl636300194;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地址',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `isdefault` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '否' COMMENT '是否默认地址',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '2025-04-06 15:12:38', '地址1', '李一', '19819881111', '是', 11);
INSERT INTO `address` VALUES (2, '2025-04-06 15:12:38', '地址2', '王二', '19819882222', '是', 12);
INSERT INTO `address` VALUES (3, '2025-04-06 15:12:38', '地址3', '张三', '19819883333', '是', 13);
INSERT INTO `address` VALUES (4, '2025-04-06 15:12:38', '地址4', '刘四', '19819884444', '是', 14);
INSERT INTO `address` VALUES (5, '2025-04-06 15:12:38', '地址5', '陈五', '19819885555', '是', 15);
INSERT INTO `address` VALUES (6, '2025-04-06 15:12:38', '地址6', '杨六', '19819886666', '是', 16);
INSERT INTO `address` VALUES (7, '2025-04-06 15:12:38', '地址7', '赵七', '19819887777', '是', 17);
INSERT INTO `address` VALUES (8, '2025-04-06 15:12:38', '地址8', '黄八', '19819888888', '是', 18);
INSERT INTO `address` VALUES (9, '2025-04-06 15:12:38', '地址9', '周九', '19819889999', '是', 19);
INSERT INTO `address` VALUES (10, '2025-04-06 15:12:38', '地址10', '吴十', '19819880000', '是', 20);
INSERT INTO `address` VALUES (11, '2026-03-30 09:38:06', '111', '156', '15611111111', '是', 51);
INSERT INTO `address` VALUES (12, '2026-03-30 09:48:26', '123', '156', '15611111111', '是', 53);
INSERT INTO `address` VALUES (13, '2026-03-30 09:50:10', '123', '156', '15611111111', '是', 52);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'shangpinxinxi' COMMENT '商品表名',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `buynumber` int(11) NULL DEFAULT NULL COMMENT '购买数量',
  `price` double NULL DEFAULT NULL COMMENT '单价',
  `discountprice` double NULL DEFAULT NULL COMMENT '折扣价',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `shangjia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, '2026-03-30 16:22:15', 'shangpinxinxi', 1, '商品名称1', 'file/shangpinxinxiShangpintupian1.jpg', 1, 99.9, NULL, 51, '商家1');

-- ----------------------------
-- Table structure for chat_friend
-- ----------------------------
DROP TABLE IF EXISTS `chat_friend`;
CREATE TABLE `chat_friend`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `fid` bigint(20) NOT NULL COMMENT '好友id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图片',
  `role` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '表名',
  `alias` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '别名',
  `type` int(11) NULL DEFAULT 0 COMMENT '类型(0:好友申请，1:好友，2:消息)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '好友表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat_friend
-- ----------------------------
INSERT INTO `chat_friend` VALUES (1, '2025-04-06 15:12:38', 1, 1, '名称1', 'file/chat_friendPicture1.jpg,file/chat_friendPicture2.jpg,file/chat_friendPicture3.jpg', '角色1', '表名1', '别名1', 1);
INSERT INTO `chat_friend` VALUES (2, '2025-04-06 15:12:38', 2, 2, '名称2', 'file/chat_friendPicture2.jpg,file/chat_friendPicture3.jpg,file/chat_friendPicture4.jpg', '角色2', '表名2', '别名2', 2);
INSERT INTO `chat_friend` VALUES (3, '2025-04-06 15:12:38', 3, 3, '名称3', 'file/chat_friendPicture3.jpg,file/chat_friendPicture4.jpg,file/chat_friendPicture5.jpg', '角色3', '表名3', '别名3', 3);
INSERT INTO `chat_friend` VALUES (4, '2025-04-06 15:12:38', 4, 4, '名称4', 'file/chat_friendPicture4.jpg,file/chat_friendPicture5.jpg,file/chat_friendPicture6.jpg', '角色4', '表名4', '别名4', 4);
INSERT INTO `chat_friend` VALUES (5, '2025-04-06 15:12:38', 5, 5, '名称5', 'file/chat_friendPicture5.jpg,file/chat_friendPicture6.jpg,file/chat_friendPicture7.jpg', '角色5', '表名5', '别名5', 5);
INSERT INTO `chat_friend` VALUES (6, '2025-04-06 15:12:38', 6, 6, '名称6', 'file/chat_friendPicture6.jpg,file/chat_friendPicture7.jpg,file/chat_friendPicture8.jpg', '角色6', '表名6', '别名6', 6);
INSERT INTO `chat_friend` VALUES (7, '2025-04-06 15:12:38', 7, 7, '名称7', 'file/chat_friendPicture7.jpg,file/chat_friendPicture8.jpg,file/chat_friendPicture9.jpg', '角色7', '表名7', '别名7', 7);
INSERT INTO `chat_friend` VALUES (8, '2025-04-06 15:12:38', 8, 8, '名称8', 'file/chat_friendPicture8.jpg,file/chat_friendPicture9.jpg,file/chat_friendPicture10.jpg', '角色8', '表名8', '别名8', 8);
INSERT INTO `chat_friend` VALUES (9, '2025-04-06 15:12:38', 9, 9, '名称9', 'file/chat_friendPicture9.jpg,file/chat_friendPicture10.jpg,file/chat_friendPicture11.jpg', '角色9', '表名9', '别名9', 9);
INSERT INTO `chat_friend` VALUES (10, '2025-04-06 15:12:38', 10, 10, '名称10', 'file/chat_friendPicture10.jpg,file/chat_friendPicture11.jpg,file/chat_friendPicture12.jpg', '角色10', '表名10', '别名10', 10);
INSERT INTO `chat_friend` VALUES (11, '2026-03-30 20:29:09', 51, 131, '商家1', 'file/shangjiaTouxiang1.jpg', NULL, 'shangjia', NULL, 2);
INSERT INTO `chat_friend` VALUES (12, '2026-03-30 20:29:09', 131, 51, '用户1', 'file/yonghuTouxiang1.jpg', NULL, 'yonghu', NULL, 2);

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `fid` bigint(20) NOT NULL COMMENT '好友id',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `format` int(11) NULL DEFAULT NULL COMMENT '格式(1:文字，2:图片)',
  `is_read` int(11) NULL DEFAULT 0 COMMENT '消息已读(0:未读，1:已读)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat_message
-- ----------------------------
INSERT INTO `chat_message` VALUES (1, '2025-04-06 15:12:38', 1, 1, '内容1', 1, 1);
INSERT INTO `chat_message` VALUES (2, '2025-04-06 15:12:38', 2, 2, '内容2', 2, 2);
INSERT INTO `chat_message` VALUES (3, '2025-04-06 15:12:38', 3, 3, '内容3', 3, 3);
INSERT INTO `chat_message` VALUES (4, '2025-04-06 15:12:38', 4, 4, '内容4', 4, 4);
INSERT INTO `chat_message` VALUES (5, '2025-04-06 15:12:38', 5, 5, '内容5', 5, 5);
INSERT INTO `chat_message` VALUES (6, '2025-04-06 15:12:38', 6, 6, '内容6', 6, 6);
INSERT INTO `chat_message` VALUES (7, '2025-04-06 15:12:38', 7, 7, '内容7', 7, 7);
INSERT INTO `chat_message` VALUES (8, '2025-04-06 15:12:38', 8, 8, '内容8', 8, 8);
INSERT INTO `chat_message` VALUES (9, '2025-04-06 15:12:38', 9, 9, '内容9', 9, 9);
INSERT INTO `chat_message` VALUES (10, '2025-04-06 15:12:38', 10, 10, '内容10', 10, 10);
INSERT INTO `chat_message` VALUES (11, '2026-03-30 20:29:09', 51, 131, '111', 1, 1);
INSERT INTO `chat_message` VALUES (12, '2026-03-30 20:29:09', 51, 131, 'file/shangpinxinxiShangpintupian1.jpg', 2, 1);
INSERT INTO `chat_message` VALUES (13, '2026-03-30 20:29:21', 131, 51, '666', 1, 1);

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '值',
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '轮播图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, '2025-04-06 15:12:38', 'swiper1', 'file/swiperPicture1.jpg', NULL);
INSERT INTO `config` VALUES (2, '2025-04-06 15:12:38', 'swiper2', 'file/swiperPicture2.jpg', NULL);
INSERT INTO `config` VALUES (3, '2025-04-06 15:12:38', 'swiper3', 'file/swiperPicture3.jpg', NULL);

-- ----------------------------
-- Table structure for dingdanpeisong
-- ----------------------------
DROP TABLE IF EXISTS `dingdanpeisong`;
CREATE TABLE `dingdanpeisong`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangpinmingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `yonghu` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户',
  `xingming` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `peisongzhuangtai` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配送状态',
  `gengxinshijian` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `peisongyuan` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配送员',
  `shangjia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单配送' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dingdanpeisong
-- ----------------------------
INSERT INTO `dingdanpeisong` VALUES (1, '2025-04-06 15:12:38', '商品名称1', '用户1', '姓名1', '已送达', '2025-04-06 15:12:38', '配送员1', '商家1');
INSERT INTO `dingdanpeisong` VALUES (2, '2025-04-06 15:12:38', '商品名称2', '用户2', '姓名2', '已送达', '2025-04-06 15:12:38', '配送员2', '商家2');
INSERT INTO `dingdanpeisong` VALUES (3, '2025-04-06 15:12:38', '商品名称3', '用户3', '姓名3', '已送达', '2025-04-06 15:12:38', '配送员3', '商家3');
INSERT INTO `dingdanpeisong` VALUES (4, '2025-04-06 15:12:38', '商品名称4', '用户4', '姓名4', '已送达', '2025-04-06 15:12:38', '配送员4', '商家4');
INSERT INTO `dingdanpeisong` VALUES (5, '2025-04-06 15:12:38', '商品名称5', '用户5', '姓名5', '已送达', '2025-04-06 15:12:38', '配送员5', '商家5');
INSERT INTO `dingdanpeisong` VALUES (6, '2025-04-06 15:12:38', '商品名称6', '用户6', '姓名6', '已送达', '2025-04-06 15:12:38', '配送员6', '商家6');
INSERT INTO `dingdanpeisong` VALUES (7, '2025-04-06 15:12:38', '商品名称7', '用户7', '姓名7', '已送达', '2025-04-06 15:12:38', '配送员7', '商家7');
INSERT INTO `dingdanpeisong` VALUES (8, '2025-04-06 15:12:38', '商品名称8', '用户8', '姓名8', '已送达', '2025-04-06 15:12:38', '配送员8', '商家8');
INSERT INTO `dingdanpeisong` VALUES (9, '2025-04-06 15:12:38', '商品名称9', '用户9', '姓名9', '已送达', '2025-04-06 15:12:38', '配送员9', '商家9');
INSERT INTO `dingdanpeisong` VALUES (10, '2025-04-06 15:12:38', '商品名称10', '用户10', '姓名10', '已送达', '2025-04-06 15:12:38', '配送员10', '商家10');
INSERT INTO `dingdanpeisong` VALUES (11, '2026-03-30 14:33:57', '123', '用户3', '姓名3', '未送达', '2026-03-30 14:33:47', '配送员9', '商家1');

-- ----------------------------
-- Table structure for discussshangpinxinxi
-- ----------------------------
DROP TABLE IF EXISTS `discussshangpinxinxi`;
CREATE TABLE `discussshangpinxinxi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `score` double NULL DEFAULT NULL COMMENT '评分',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品信息评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussshangpinxinxi
-- ----------------------------

-- ----------------------------
-- Table structure for discusstuangoushangpin
-- ----------------------------
DROP TABLE IF EXISTS `discusstuangoushangpin`;
CREATE TABLE `discusstuangoushangpin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `score` double NULL DEFAULT NULL COMMENT '评分',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '团购商品评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discusstuangoushangpin
-- ----------------------------
INSERT INTO `discusstuangoushangpin` VALUES (1, '2026-03-30 20:12:42', 11, 51, 'file/yonghuTouxiang1.jpg', '用户1', 5, '<p>111</p>', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menujson` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '2025-04-06 15:12:38', '[{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"导出\",\"首页总数\",\"私信\"],\"appFrontIcon\":\"cuIcon-goodsnew\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"导出\",\"首页总数\",\"私信\"],\"classname\":\"yonghu\",\"menu\":\"用户\",\"menuJump\":\"列表\",\"tableName\":\"yonghu\"}],\"fontClass\":\"icon-user4\",\"menu\":\"用户管理\",\"unicode\":\"&#xef9a;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已取消订单\",\"menuJump\":\"已取消\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"发货\",\"物流\",\"核销\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已支付订单\",\"menuJump\":\"已支付\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"取消拼团\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"拼团中订单\",\"menuJump\":\"拼团中\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"商品订单\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已退款订单\",\"menuJump\":\"已退款\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已完成订单\",\"menuJump\":\"已完成\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"未支付订单\",\"menuJump\":\"未支付\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"确认收货\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已发货订单\",\"menuJump\":\"已发货\",\"tableName\":\"orders\"}],\"menu\":\"订单管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"shangpinfenlei\",\"menu\":\"商品分类\",\"menuJump\":\"列表\",\"tableName\":\"shangpinfenlei\"}],\"fontClass\":\"icon-common38\",\"menu\":\"商品分类管理\",\"unicode\":\"&#xeeb2;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goodsnew\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"users\",\"menu\":\"管理员\",\"menuJump\":\"列表\",\"tableName\":\"users\"}],\"fontClass\":\"icon-user3\",\"menu\":\"管理员管理\",\"unicode\":\"&#xef99;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"dingdanpeisong\",\"menu\":\"订单配送\",\"menuJump\":\"列表\",\"tableName\":\"dingdanpeisong\"}],\"fontClass\":\"icon-common40\",\"menu\":\"订单配送管理\",\"unicode\":\"&#xeebb;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"查看\",\"修改\"],\"classname\":\"config\",\"menu\":\"轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"}],\"fontClass\":\"icon-common35\",\"menu\":\"轮播图管理\",\"unicode\":\"&#xee8c;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"peisongyuan\",\"menu\":\"配送员\",\"menuJump\":\"列表\",\"tableName\":\"peisongyuan\"}],\"fontClass\":\"icon-common9\",\"menu\":\"配送员管理\",\"unicode\":\"&#xedc9;\"},{\"child\":[{\"allButtons\":[\"菜单管理\"],\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"菜单管理\"],\"classname\":\"menu\",\"menu\":\"菜单\",\"menuJump\":\"列表\",\"tableName\":\"menu\"}],\"fontClass\":\"icon-common39\",\"menu\":\"菜单管理\",\"unicode\":\"&#xeeba;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"导出\",\"私信\"],\"appFrontIcon\":\"cuIcon-clothes\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"导出\"],\"classname\":\"shangjia\",\"menu\":\"商家\",\"menuJump\":\"列表\",\"tableName\":\"shangjia\"}],\"fontClass\":\"icon-user6\",\"menu\":\"商家管理\",\"unicode\":\"&#xef9c;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"首页总数\",\"私信\",\"发布团购\",\"配送\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"首页总数\"],\"classname\":\"shangpinxinxi\",\"menu\":\"商品信息\",\"menuJump\":\"列表\",\"tableName\":\"shangpinxinxi\"}],\"fontClass\":\"icon-common38\",\"menu\":\"普通商品管理\",\"unicode\":\"&#xeeb2;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"广告点击量\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"广告点击量\",\"首页总数\",\"首页统计\"],\"classname\":\"news\",\"menu\":\"广告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"fontClass\":\"icon-common26\",\"menu\":\"公告资讯管理\",\"unicode\":\"&#xee2b;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"私信\",\"配送\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"classname\":\"tuangoushangpin\",\"menu\":\"团购商品\",\"menuJump\":\"列表\",\"tableName\":\"tuangoushangpin\"}],\"fontClass\":\"icon-common22\",\"menu\":\"团购商品管理\",\"unicode\":\"&#xee04;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-qrcode\",\"buttons\":[\"查看\"],\"classname\":\"cart\",\"menu\":\"购物车\",\"menuJump\":\"列表\",\"tableName\":\"cart\"}],\"menu\":\"购物车管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"广告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"公告资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"shangpinxinxi\",\"menu\":\"商品信息\",\"menuJump\":\"列表\",\"tableName\":\"shangpinxinxi\"}],\"menu\":\"商品信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"tuangoushangpin\",\"menu\":\"团购商品\",\"menuJump\":\"列表\",\"tableName\":\"tuangoushangpin\"}],\"menu\":\"商品信息管理\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"pathName\":\"users\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"发货\",\"物流\",\"核销\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"menu\":\"已支付订单\",\"menuJump\":\"已支付\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"menu\":\"商品订单\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"menu\":\"已退款订单\",\"menuJump\":\"已退款\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"menu\":\"已完成订单\",\"menuJump\":\"已完成\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"menu\":\"未支付订单\",\"menuJump\":\"未支付\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"确认收货\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\",\"确认收货\",\"物流\"],\"menu\":\"已发货订单\",\"menuJump\":\"已发货\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"menu\":\"已取消订单\",\"menuJump\":\"已取消\",\"tableName\":\"orders\"}],\"menu\":\"订单管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\"],\"classname\":\"cart\",\"menu\":\"购物车\",\"menuJump\":\"列表\",\"tableName\":\"cart\"}],\"fontClass\":\"icon-common43\",\"menu\":\"购物车管理\",\"unicode\":\"&#xef27;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"classname\":\"storeup\",\"menu\":\"我的收藏\",\"menuJump\":\"1\",\"tableName\":\"storeup\"}],\"fontClass\":\"icon-common25\",\"menu\":\"我的收藏管理\",\"unicode\":\"&#xee09;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\"],\"classname\":\"dingdanpeisong\",\"menu\":\"订单配送\",\"menuJump\":\"列表\",\"tableName\":\"dingdanpeisong\"}],\"fontClass\":\"icon-common40\",\"menu\":\"订单配送管理\",\"unicode\":\"&#xeebb;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"address\",\"menu\":\"地址\",\"menuJump\":\"列表\",\"tableName\":\"address\"}],\"fontClass\":\"icon-common3\",\"menu\":\"地址管理\",\"unicode\":\"&#xeda5;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-qrcode\",\"buttons\":[\"查看\"],\"classname\":\"cart\",\"menu\":\"购物车\",\"menuJump\":\"列表\",\"tableName\":\"cart\"}],\"menu\":\"购物车管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"广告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"公告资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"shangpinxinxi\",\"menu\":\"商品信息\",\"menuJump\":\"列表\",\"tableName\":\"shangpinxinxi\"}],\"menu\":\"商品信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"tuangoushangpin\",\"menu\":\"团购商品\",\"menuJump\":\"列表\",\"tableName\":\"tuangoushangpin\"}],\"menu\":\"商品信息管理\"}],\"hasBackLogin\":\"否\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"pathName\":\"yonghu\",\"roleName\":\"用户\",\"tableName\":\"yonghu\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已完成订单\",\"menuJump\":\"已完成\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"未支付订单\",\"menuJump\":\"未支付\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"确认收货\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已发货订单\",\"menuJump\":\"已发货\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已取消订单\",\"menuJump\":\"已取消\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"发货\",\"物流\",\"核销\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"发货\",\"物流\"],\"menu\":\"已支付订单\",\"menuJump\":\"已支付\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"取消拼团\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"拼团中订单\",\"menuJump\":\"拼团中\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"商品订单\",\"tableName\":\"orders\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\",\"物流\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"月销量\",\"日销量\",\"导出\",\"首页总数\",\"首页统计\"],\"menu\":\"已退款订单\",\"menuJump\":\"已退款\",\"tableName\":\"orders\"}],\"menu\":\"订单管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"dingdanpeisong\",\"menu\":\"订单配送\",\"menuJump\":\"列表\",\"tableName\":\"dingdanpeisong\"}],\"fontClass\":\"icon-common40\",\"menu\":\"订单配送管理\",\"unicode\":\"&#xeebb;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\"],\"classname\":\"shangpinfenlei\",\"menu\":\"商品分类\",\"menuJump\":\"列表\",\"tableName\":\"shangpinfenlei\"}],\"fontClass\":\"icon-common38\",\"menu\":\"商品分类管理\",\"unicode\":\"&#xeeb2;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"查看\"],\"classname\":\"peisongyuan\",\"menu\":\"配送员\",\"menuJump\":\"列表\",\"tableName\":\"peisongyuan\"}],\"fontClass\":\"icon-common9\",\"menu\":\"配送员管理\",\"unicode\":\"&#xedc9;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"私信\",\"配送\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"查看评论\",\"配送\"],\"classname\":\"tuangoushangpin\",\"menu\":\"团购商品\",\"menuJump\":\"列表\",\"tableName\":\"tuangoushangpin\"}],\"fontClass\":\"icon-common22\",\"menu\":\"团购商品管理\",\"unicode\":\"&#xee04;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"广告点击量\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"新增\",\"查看\",\"广告点击量\",\"首页总数\",\"首页统计\"],\"classname\":\"news\",\"menu\":\"广告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"fontClass\":\"icon-common26\",\"menu\":\"公告资讯管理\",\"unicode\":\"&#xee2b;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"首页总数\",\"私信\",\"发布团购\",\"配送\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"首页总数\",\"发布团购\",\"配送\"],\"classname\":\"shangpinxinxi\",\"menu\":\"商品信息\",\"menuJump\":\"列表\",\"tableName\":\"shangpinxinxi\"}],\"fontClass\":\"icon-common38\",\"menu\":\"普通商品管理\",\"unicode\":\"&#xeeb2;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-qrcode\",\"buttons\":[\"查看\"],\"classname\":\"cart\",\"menu\":\"购物车\",\"menuJump\":\"列表\",\"tableName\":\"cart\"}],\"menu\":\"购物车管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"广告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"公告资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"shangpinxinxi\",\"menu\":\"商品信息\",\"menuJump\":\"列表\",\"tableName\":\"shangpinxinxi\"}],\"menu\":\"商品信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"tuangoushangpin\",\"menu\":\"团购商品\",\"menuJump\":\"列表\",\"tableName\":\"tuangoushangpin\"}],\"menu\":\"商品信息管理\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"是\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"pathName\":\"shangjia\",\"roleName\":\"商家\",\"tableName\":\"shangjia\"}]');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `introduction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '简介',
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `click_number` int(11) NULL DEFAULT 0 COMMENT '点击次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '广告信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '2025-04-06 15:12:38', '余生，愿你活出自己的精彩', '曾几何时，为了让自己显得合群，选择去做自己不喜欢的事;为了讨好喜欢的人，一次次地委屈自己;甚至，为了陌生人的一个眼神，就陷入了深深的自我怀疑。', 'file/newsPicture1.jpg', '可是，这样小心翼翼的你，一定很累吧。你以为照顾了所有人的想法，就能得到他们的喜欢;你以为逼着自己合群，就能真的合群。直到后来才发现，在日复一日对自己的为难中，你逐渐弄丢了真实的自己.\n殊不知，人生在世，最不值得的事情，就是在别人的眼光中，迷失自己。其实，正如世界上没有十全十美的人，你也同样无法做到让每个人满意。\n世上没有不快乐的人，只有不肯让自己快乐的心。有一句话说:\n如果一段关系让你变得卑微，你可以选择抽身离去;如果一个人总是让你悲伤哭泣，你要学会放手。\n始终相信，每个人最初的样子，都是最美好的。真正喜欢你的人，会喜欢你原来的样子:真正爱你的从来不需要你的委曲求全。\n后半生，请把身上的枷锁，全部解除。去做喜欢的事，去听自己内心的声音。你会发现，这世上没有什么事情比活出自己更加幸福。\n要相信，真实的自己亦是一道风景线。你若盛开，清风自来。愿你此生尽兴，不负韶华，', 2);
INSERT INTO `news` VALUES (2, '2025-04-06 15:12:38', '岁月无常，好好珍惜', '常听到这样的话，如果一切可以重新开始，我一定改掉任性，学会坚持，如果有下辈子，我一定好好珍惜，不会再弄丢原本属于自己的幸福。', 'file/newsPicture2.jpg', '然而，世上有很多事情没有再来一次的机会，一旦错失就意味着无法弥补，正如人与人之间，有的只是一世的缘分，一旦转身，就意味着再无可能。\n这一生只活一次，有些人也只爱一回。\n所以，在拥有时就好好把握，对于来世，可以适当憧憬，但不要一昧去幻想，活着，唯有珍惜身边做好眼前事，才能让明天少一些遗憾。人，\n既然有幸来到这个世上，那就要努力把这辈子活好。岁月其实远没有想象中的那么漫长，若是能爱就好好爱，别让说好相伴一生的人，中途散走;若是当下能相知相守，就别去等待虚无缥缈的未来。\n就像一位网友留言说的:我从来不去期待下辈子，我要的只有今生，世事无常，来世是怎样的光景，谁都无法预知。对我来说，在有限的时光里，善待爱自己及自己爱的人，用心珍惜相聚的每一时刻，努力过好当下就足够了。\n时光匆匆，韶华似水，每个人都只有这短暂的一生。\n有些事，如果喜欢就坚定努力的去做，有些人，如果爱就真诚用心地去爱，别幻想着重来，别总是寄希望于下辈子。\n下辈子，彼此也许再也不会遇见，就算遇见了，谁还会记得谁，今生相互承诺的两个人，来生也许相见不相识。\n想起之前看过的一句话:人生没有假如，我们没有来世，下辈子，你我也许只是大千世界里互不相识的两个陌生人。\n的确，生命只有一次，今生有幸相遇的人，下辈子不一定能遇见，就算能遇见，谁能把谁陪伴，谁又是谁的谁?\n余生不长，趁着岁月未老，好好珍惜这辈子的缘分吧，别等到失去了才悔不该当初。', 2);
INSERT INTO `news` VALUES (3, '2025-04-06 15:12:38', '与其背着烦恼活，不如带着美好过', '一直觉得，感情里，无疾而终并不可怕，可怕的是结束后，你还抱着回忆不肯放;岁月中，失去并不可怕，可怕的是失去后，你还揪着曾经不肯忘。\n有些事，明知道不应该继续，却始终没有转身的勇气;有些人，明知道已经成为过去，却还是舍不得放手;有时候，明知道纠缠于往事旧人只会徒增伤悲，却仍然执着的去回忆，去留恋。', 'file/newsPicture3.jpg', '人生短短几十年，为什么要蹉跎浪费在无可挽回的失去上，为什么不酒脱一点，看开一点，活得轻松快乐一点。\n这世间，没有谁的人生是十全十美的，总要面对一些离别，总要经历一些失去，如果总对身边的一切执念太深，只会越活越累。\n时间不停向前，人也不可避免的要向前看。面对一些不如意、不顺心的事，该放的就放，该忘的就忘，别总把自己困在失败的悲伤中。\n生而为人，放不是无能，而是一种洒脱，忘更不是懦弱，而是一种智慧。\n在来去匆匆，聚散不定的现实里，放下不属于自己的感情，才能有新的开始，忘记心中的烦恼，才能收获愉快的心情。\n所以，有些离开的人，该放就放下吧，一直念念不忘，伤害的是自己，有些烦心事，该忘就忘了吧一直耿耿于怀，痛苦的是自己。\n-辈子本就不长，何必让自己活得那么累，过去的情就让它过去，别再留恋，想不通的事就算了，别再纠结。\n人生的旅途，总有些事让人烦，总有些情让人伤。只有学会忘记过往的不如意，忘记一些不必要的烦恼，以轻盈的身心重新出发，才不会被旧人旧事所累。\n记得，每个人心灵的空间都是有限的，当装多了纠结与伤感，就装不下自在与欢快了。要想日子过得简单轻松一点，就要懂得放下执念，忘掉痛苦，清除伤。', 4);
INSERT INTO `news` VALUES (4, '2025-04-06 15:12:38', '理想很丰满，现实很骨感', '游戏里的你帅气无比，乘风御剑，通关杀敌无所不能，你满足于那其中的成就感，你满足于通关的畅快淋漓。', 'file/newsPicture4.jpg', '年轻人，可那毕竟不是现实，理想很丰满，现实很骨感这不是玩笑，你不走出来，怎么会知\n你真的想等到某天喜欢上一个姑娘时，才去纠结咖啡钱电影钱你要如何与爸妈张口，才去苦恼为她买礼物你要怎样省吃俭用节衣缩食?\n你没有挥金如土的.魄力，没有殷实的家境，没有既定的光芒锦绣的前程，二十出头的年纪，你不努力，你想拿什么报答那个在你身边用最好的青春陪伴你的姑娘?你又拿什么报答含辛茹苦养育你这么多年的父母?\n生活不是游戏，不会给你那么多反复再来的机会，青春年少风华正茂，年轻人，你不努力，你想指望什么?\n年轻人，走出来奋斗吧!累也好苦也罢。如果你现在对自己各种放纵，你指望以后你用什么条件来放松?\n年轻人，你要努力赚钱，为了自己，也为了你爸妈。为了他们在以后逛商场的时候，可以给自己买东西像给你买东西一样毫不犹豫。为了他们可以在同老友谈起你时可以一脸安详，而不是想着，这个月，儿子交了房租还有钱吃饭么。我们这个年龄的人，已经像吸血鬼一样巴着父母活了二十多年了，还真的要继续吸着他们的血活下去么?\n年轻人，停止期望凡事简单容易。如果总是挑简单的事情做，那你永远成不了大器，生活总会在始料不及时考验你，所以你必须挑战困难，保持坚强，世上任何值得拥有的东西都需要付诸努力，勤奋与自律，倘若自甘平庸，那你就会变得庸碌无为。\n年轻人，与我们一起，挑战一下自己，拥抱一下梦想。你想要的生活，跳起来，一定够得到!', 4);
INSERT INTO `news` VALUES (5, '2025-04-06 15:12:38', '理想是一种精神上的追求', '如果前方是一片黑暗，理想是否会黯淡?黑暗终究不能限制理想的步伐，只是需要时间。', 'file/newsPicture5.jpg', '是否会撕裂黑暗的结界?给理想一点时间，是否会完成理想的目标?太多时候，黑给黑暗一缕曙光，暗限制了理想的脚步，时间成了撕裂黑暗的曙光。社会的压抑，未知的迷茫，甚至是事实的打击，都成了所谓的黑暗。\n理想应该是一种精神上的追求，一种我们内心深处的渴望。也许理想的种类太多，不一样的人也有不一样的理想。但唯一的共同点就是我们在追求，追求自己精神上亦或是心中的目标。但总是不尽人意，一来存在太多不确定因素，二来时间还不够。理想并不是一而就的空话。\n想过放弃，因为理想与现实的差距。但又不想放弃，因为理想是精神上的追求心中的渴望。纠结了太多，却是否想过在纠结的时候，已经选择了错过。被刻意选择的错过是什么?是路过。我们在不经意间路过了多少理想，只是因为纠结。如果坚定自己的理想，用时间去行动，是否会少几次刻意的错过:-次，与一位朋友谈到如何乐观，然后追求理想。朋友想了一会儿，给我的回答是正能量与时间。他说，想要乐观就要不断给自己正能量。事物有阴阳两面，如果阳代表乐观，那在我们看待一件事时要看阳面千万别看阴面。这样自己不会消极，某种程度上来说就是一种正能量。想要追求理想，务必要花费时间。正如没有风平浪静的大海，想要追求理想，要时间。给理想一点时间，让自己去追求，不顾切的追求。\n点点滴滴的小事构成了我们的生活，点点滴滴的努力给理想铺平了路。每一次小成功都为理想迈进了一步。每一次失败，都告诉我们一些教训，反方向来说为下一次成功提供了经验。不要觉得失败是可怕的，长远来看，正是失败才有了成功。\n时间在流逝。如果安于现状，就会乐不思。理想需要时间不代表我们可以挥霍，我们能做的是珍惜时间，去为理想拼搏。\n间接意义上讲，理想的时间是我们的努力。\n如果正在路上，一直任性向前走，忘记时间不回头。', 5);
INSERT INTO `news` VALUES (6, '2025-04-06 15:12:38', '人生如梦，梦想是帆', '人生如梦，梦想是帆，每个人都有一个只属于自己的梦，但我们同属一个国家，所以每个人的梦又与国家的兴衰荣辱紧密相连。先哲顾炎武曾说:天下兴亡，匹夫有责。只有国家好，大家才能好。', 'file/newsPicture6.jpg', '我依然清楚的记得:\n当甲午战争战败，日寇无礼踏破中国的门户;当八国联军侵入北京，无情掠夺中国的财产:当七七事变发生，中国的老人、妇孺被残忍杀害的时候，我在想那时中国的梦是怎样的!\n我虽不曾亲眼看到，但那却是铁一般的事实。因为从老人们那深邃的眼神中可以感到无尽的愤懑;从他们干瘪的脸颊可以看到深情的泪水，从他们嘹亮的军歌中可以想到那奋勇杀敌时的豪迈;从他们激昂话语中听到那誓要捍卫家园振兴中华的誓言。作为新一代青年的我们难道不应该树立远大的理想，付之以踏实的行动，去继承先辈们的使命。去实现中华民族的伟大崛起和复兴吗?\n有梦才能使中国繁荣!\n在改革开放以来中国取得了一系列的可以载入中国史册的成就。香港、澳门的回归，经济特区的建立，使中国成为发展国家中的经济大国，科技先进国和军事强国。当中国成功举办奥运的时候，当神九飞天的时候，当蛟龙入海的时候，当航母下水的时候，当莫言荣获诺贝尔文学奖的时候。我相信每个人都感觉到了无比的自豪。但是现在的中国与其他发达国家还有很大差距。作为新一代的我们，难道不应该志存高远吗?\n我想有的人会说，我们的力量是有限的。的确个人的力量很渺小，但是中国梦就是因一个个微不足道的个人的梦一直汇集、汇集，然后凝聚成的一个巨大的梦。冯至在《十四行诗》中写道，我们准备着，深深领受，那些意想不到的奇迹，在漫长的岁月里，忽然有彗星的出现，狂风乍起。\n梦想是美丽的，它是美的期望;梦想是阳光的，它使人由浮躁走向踏实;梦想是充满力量的，它可以激发人身体里无限的潜能。我们期盼的是国泰民安、经济发展、政治清明、文化繁荣、社会和谐、生态良\n好、公平正义。这才是中国人伟大的梦', 6);
INSERT INTO `news` VALUES (7, '2025-04-06 15:12:38', '若月亮没来-王宇宙Leto，乔浚丞', '《若月亮没来(若是月亮还没来)》是王宇宙Leto，乔浚丞演唱的歌曲，于2024年1月22日上线发行。', 'file/newsPicture7.jpg', '风吹过山 船靠了岸  风光呀 一点点看  我走向北 你去往南\n故事呀 一篇篇翻  好烦 又加班到很晚  你搭上空荡的 地铁已是末班\n好烦 很爱却要分开  恋爱谈不明白  好烦 接近理想好难 却又还很不甘\n如何拥抱平淡  如果 最难得到圆满  不如选择坦然 若是月亮还没来\n路灯也可照窗台  照着白色的山茶花微微开  若是晨风还没来\n晚风也可吹入怀  吹着那一地树影温柔摇摆  你总以为你不够好\n不够苗条和美貌  可是完美谁能达到  做你自己就很好\n烦恼烦恼拥有太少  没有房车没有钞票  可爱你的人永远会 \n把你当做心尖珍宝  我也懂大多数的时候  你只想逃离这世界\n我也懂太多的情绪在  一个人失眠的深夜 你可以是悲伤或者埋怨\n但请不要放弃明天  这一直灰暗的世界  我想看见你的笑脸\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n李哲今年1月份创作了《若月亮没来》这首歌。随着歌曲的热度不断上升，约他写歌的人越来越多，他的工作变得更加繁忙。\n近年来，李哲凭借对音乐的热爱和不懈的努力，创作了几百首音乐作品，其中不乏广为人知的佳作，例如王栎鑫的《一个人》、何赛飞与叶炫清在央视演唱的《木兰诗》等歌曲，都是由李哲作曲。\n如今，李哲的音乐才华和努力得到了业界的广泛认可，他的音乐作品多次登上各大音乐平台的热歌榜，成为备受瞩目的新生代音乐人。现在，走在街头、坐在车里，经常会听到自己的作品。看到自己的音乐作品受到如此广泛的喜爱和认可，我感到非常有成就感。李哲笑着说，他会努力创作更多优秀音乐作品，为家乡争光。', 7);
INSERT INTO `news` VALUES (8, '2025-04-06 15:12:38', '-米阳光传递一份温暖', '黑暗中跌倒，坚强里苦笑。只是因为青春还在，所以梦想一直在路上。当年华未曾老去，青春没有散场，留给幽暗的岁月，还有几分值得珍藏?孤独无助时、伤心难过时总爱恣意挥霍愤怒。伤了、痛了原本以为就都结束了;可是心里还不甘愿放弃，就只为一个理由，有梦想的人永不孤单。', 'file/newsPicture8.jpg', '放进去了的青春，走不出来的悲伤却总是给年少时的我们留下太多无法割舍的情怀。记录岁月的足迹，聆听时光的故事。然轻吟浅唱出，青春嘉年华。\n故事里总会有，那些太多感慨于时间的无情，太多对往事的追忆，太多的太多，而今刻在了心里也停泻在了流年里。一段无法抹去的回忆，也只不过是一场烟火燃放的瞬间，却也一辈子在脑海里兜兜转转。很是怀念那些相遇的日子，邂逅了幸福也留在了时间轨迹里。时而想起，那些曾肩并肩奋斗的日子，那些写满感动与快乐的岁月。\n如今，我们似乎渐行渐远，彼此奋斗着青春的轮回。渐渐的，我们都长大了，时间改变了我们时刻分享彼此快乐与不快乐的习惯，青春的渐逝，岁月的更迭让我们变得都不再联系，身边的朋友也越来越少;那些欢笑，那些幸福的流年也随着我们的陌生而变得更加奢侈。也许多年以后，我们都有了自己的朋友，有了自己关心的人。那时，我们再细数那些癫狂在你生命里一辈子的记忆故事，蓦然发现一切都停留在了回忆里。', 8);
INSERT INTO `news` VALUES (9, '2025-04-06 15:12:38', '自己的路自己走，自己的心自己懂', '岛上书店》中有句话:每个人的生命里，都有最艰难的那一年，将人生变得美好而辽阔。\n你有没有经历过那么一年，在走过的岁月中跋涉得异常艰难困苦，却仍无法灭前行的勇气。哪怕所有人都极力劝阻，也要不甘心地试一试;哪怕前方已没有道路，却还在坚定前行;哪怕再看不到任何希望，也要竭尽全力地为自己争取一个机会。', 'file/newsPicture9.jpg', '你开始不动声色的扮演着一个大人，经历着生活日复一日的打。\n每天醒在天还没亮的五六点，挤过早高峰的地下铁，面对成山的工作，加班到深夜倒下就睡更成为了日常，还要应付各色的人际关系，逐渐开始接受努力也不一定有回报的道理。\n这段日子也许很长，但你没有放弃对生活的信心，始终相信人生总会变得一帆风顺，更坚信每件事的最后都会有一个好的结局，如果不是，说明还没到最后。\n于是你树立新的目标，让这一年的奔波更有方向。\n虽然永远无法预料明天是晴是雨，也无法预知你在乎的人是否还在身边，你一直以来的坚持究竟能换来什么。\n但你能决定的是，今天有没有备好雨伞，有没有好好爱人以及是否为自己追求的理想拼尽全力。\n永远不要只看见前方路途遥远而忘了从前的自己坚持了多久才走到了这里，\n今天拼尽全力虽然艰辛万分，在未来都将成为落在你身上的礼物。', 9);
INSERT INTO `news` VALUES (10, '2025-04-06 15:12:38', '愿你有明天可奔赴，有过往可回头', '此去经年，用一张离家的车票，换一段有故事可说的人生。\n大概就是那时候怀揣着梦想，背上了行囊，一腔热血决定远行的你。独自拎着一只行李箱，背着双肩包在深夜抵达一个陌生的城市，站在人潮涌动的车站出口，望着宽敞公路上车水马龙的喧嚣，城市里亮如白昼的繁华。', 'file/newsPicture10.jpg', '这世界上的人们都活的匆匆忙忙，每个人看起来都很焦虑，每个人都有自己的苦楚，却往往找不到人可以诉说。也许你的倾诉，对别人来说是一种打扰，而且别人没经历过你的经历，所以无法体会你的心情。我们总是在心情不好的时候拿着手机，翻翻通讯录，然后再默默放下手机，压住心中那股想要找人说说话的想法。\n等到低落的情绪慢慢有些好转，才开始明白，人这一生，无论是什么样的路，都是自己的选择，冲动也好，深思熟虑也罢，自己选的路，不管多难，都要自己走下去。心中的感受，不管是复杂还是简单，都只能自己来懂，别人帮不了你，也安慰不了你，顶多给你一些无济于事的劝告，最后还得靠自己想开。你选的幸福，你自己享受;你选的眼泪，你自己来流，生活就是如此，自己的路，自己走;自己的心，自己懂。', 10);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'shangpinxinxi' COMMENT '商品表名',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `buynumber` int(11) NULL DEFAULT NULL COMMENT '购买数量',
  `price` double NULL DEFAULT NULL COMMENT '单价',
  `discountprice` double NULL DEFAULT NULL COMMENT '折扣价',
  `total` double NULL DEFAULT NULL COMMENT '总价',
  `discounttotal` double NULL DEFAULT NULL COMMENT '折扣总价格',
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付类型',
  `status` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单状态',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `logistics` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '物流',
  `role` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户角色',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `sfsh` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '回复内容',
  `shangjia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商户名称',
  `groupno` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '团购编号',
  `tuangouid` bigint(20) NULL DEFAULT NULL COMMENT '拼团 ID',
  `groupprice` double NULL DEFAULT NULL COMMENT '团购价',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderid`(`orderid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (21, '2026-03-30 15:56:15', '20260330155614955574', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 1, 66, 66, 66, 66, '3', '已退款', '123', '15611111111', '156', '', NULL, 'yonghu', 53, NULL, NULL, '商家1', '20260330155614955642', 18, 66);
INSERT INTO `orders` VALUES (22, '2026-03-29 16:24:05', '20260330162405607405', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 5, 66, 66, 330, 330, '3', '已退款', '111', '15611111111', '156', '', '<p>111</p>', 'yonghu', 51, '是', '1', '商家1', '20260330162405607810', 19, 66);
INSERT INTO `orders` VALUES (23, '2026-03-30 16:24:28', '20260330162427871932', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 1, 66, 66, 66, 66, '3', '已支付', '123', '15611111111', '156', '', NULL, 'yonghu', 52, NULL, NULL, '商家1', '20260330162405607810', 19, 66);
INSERT INTO `orders` VALUES (24, '2026-03-28 16:24:56', '20260330162456622523', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 1, 66, 66, 66, 66, '3', '已支付', '123', '15611111111', '156', '', NULL, 'yonghu', 53, NULL, NULL, '商家1', '20260330162405607810', 19, 66);
INSERT INTO `orders` VALUES (25, '2026-02-15 16:31:43', '20260330163143129256', 'tuangoushangpin', 2, '商品名称2', 'file/tuangoushangpinShangpintupian2.jpg', 2, 9.9, 9.9, 19.8, 19.8, '3', '已支付', '123', '15611111111', '156', '', NULL, 'yonghu', 53, NULL, NULL, '商家2', '20260330163143130725', 20, 9.9);
INSERT INTO `orders` VALUES (26, '2026-02-15 16:32:06', '20260330163206008301', 'tuangoushangpin', 2, '商品名称2', 'file/tuangoushangpinShangpintupian2.jpg', 1, 9.9, 9.9, 9.9, 9.9, '3', '已支付', '123', '15611111111', '156', '', NULL, 'yonghu', 52, NULL, NULL, '商家2', '20260330163143130725', 20, 9.9);
INSERT INTO `orders` VALUES (27, '2026-03-30 16:49:12', '20260330164911937179', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 5, 66, 66, 330, 330, '3', '拼团中', '123', '15611111111', '156', '', NULL, 'yonghu', 52, NULL, NULL, '商家1', '20260330164911938465', 21, 66);
INSERT INTO `orders` VALUES (28, '2026-03-30 16:49:57', '20260330164956963858', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 8, 66, 66, 528, 528, '3', '拼团中', '111', '15611111111', '156', '', NULL, 'yonghu', 51, NULL, NULL, '商家1', '20260330164956963658', 22, 66);
INSERT INTO `orders` VALUES (29, '2026-03-30 16:50:29', '20260330165029003428', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 2, 66, 66, 132, 132, '3', '拼团中', '123', '15611111111', '156', '', NULL, 'yonghu', 53, NULL, NULL, '商家1', '20260330164911938465', 21, 66);
INSERT INTO `orders` VALUES (30, '2026-03-30 16:56:29', '20260330165629336708', 'shangpinxinxi', 1, '商品名称1', 'file/shangpinxinxiShangpintupian1.jpg', 1, 99.9, 99.9, 99.9, 99.9, '1', '已取消', '123', '15611111111', '156', '', NULL, 'yonghu', 53, NULL, NULL, '商家1', NULL, NULL, NULL);
INSERT INTO `orders` VALUES (31, '2026-03-30 20:02:57', '20260330200257056842', 'tuangoushangpin', 11, '123', 'file/1774834888839.jpg', 2, 66, 66, 132, 132, '3', '已退款', '111', '15611111111', '156', '', NULL, 'yonghu', 51, NULL, NULL, '商家1', '20260330200257056857', 23, 66);

-- ----------------------------
-- Table structure for peisongyuan
-- ----------------------------
DROP TABLE IF EXISTS `peisongyuan`;
CREATE TABLE `peisongyuan`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `peisongyuan` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配送员',
  `xingming` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `lianxifangshi` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '配送员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of peisongyuan
-- ----------------------------
INSERT INTO `peisongyuan` VALUES (1, '2025-04-06 15:12:38', '配送员1', '姓名1', '联系方式1');
INSERT INTO `peisongyuan` VALUES (2, '2025-04-06 15:12:38', '配送员2', '姓名2', '联系方式2');
INSERT INTO `peisongyuan` VALUES (3, '2025-04-06 15:12:38', '配送员3', '姓名3', '联系方式3');
INSERT INTO `peisongyuan` VALUES (4, '2025-04-06 15:12:38', '配送员4', '姓名4', '联系方式4');
INSERT INTO `peisongyuan` VALUES (5, '2025-04-06 15:12:38', '配送员5', '姓名5', '联系方式5');
INSERT INTO `peisongyuan` VALUES (6, '2025-04-06 15:12:38', '配送员6', '姓名6', '联系方式6');
INSERT INTO `peisongyuan` VALUES (7, '2025-04-06 15:12:38', '配送员7', '姓名7', '联系方式7');
INSERT INTO `peisongyuan` VALUES (8, '2025-04-06 15:12:38', '配送员8', '姓名8', '联系方式8');
INSERT INTO `peisongyuan` VALUES (9, '2025-04-06 15:12:38', '配送员9', '姓名9', '联系方式9');
INSERT INTO `peisongyuan` VALUES (10, '2025-04-06 15:12:38', '配送员10', '姓名10', '联系方式10');

-- ----------------------------
-- Table structure for shangjia
-- ----------------------------
DROP TABLE IF EXISTS `shangjia`;
CREATE TABLE `shangjia`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangjia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商家',
  `mima` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `shangjiamingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商家名称',
  `xingbie` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `touxiang` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '头像',
  `nianling` int(11) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `shangjia`(`shangjia` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangjia
-- ----------------------------
INSERT INTO `shangjia` VALUES (131, '2025-04-06 15:12:38', '商家1', '123456', '商家名称1', '男', 'file/shangjiaTouxiang1.jpg', 1);
INSERT INTO `shangjia` VALUES (132, '2025-04-06 15:12:38', '商家2', '123456', '商家名称2', '男', 'file/shangjiaTouxiang2.jpg', 2);
INSERT INTO `shangjia` VALUES (133, '2025-04-06 15:12:38', '商家3', '123456', '商家名称3', '男', 'file/shangjiaTouxiang3.jpg', 3);
INSERT INTO `shangjia` VALUES (134, '2025-04-06 15:12:38', '商家4', '123456', '商家名称4', '男', 'file/shangjiaTouxiang4.jpg', 4);
INSERT INTO `shangjia` VALUES (135, '2025-04-06 15:12:38', '商家5', '123456', '商家名称5', '男', 'file/shangjiaTouxiang5.jpg', 5);
INSERT INTO `shangjia` VALUES (136, '2025-04-06 15:12:38', '商家6', '123456', '商家名称6', '男', 'file/shangjiaTouxiang6.jpg', 6);
INSERT INTO `shangjia` VALUES (137, '2025-04-06 15:12:38', '商家7', '123456', '商家名称7', '男', 'file/shangjiaTouxiang7.jpg', 7);
INSERT INTO `shangjia` VALUES (138, '2025-04-06 15:12:38', '商家8', '123456', '商家名称8', '男', 'file/shangjiaTouxiang8.jpg', 8);
INSERT INTO `shangjia` VALUES (139, '2025-04-06 15:12:38', '商家9', '123456', '商家名称9', '男', 'file/shangjiaTouxiang9.jpg', 9);
INSERT INTO `shangjia` VALUES (140, '2025-04-06 15:12:38', '商家10', '123456', '商家名称10', '男', 'file/shangjiaTouxiang10.jpg', 10);

-- ----------------------------
-- Table structure for shangpinfenlei
-- ----------------------------
DROP TABLE IF EXISTS `shangpinfenlei`;
CREATE TABLE `shangpinfenlei`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangpinfenlei` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangpinfenlei
-- ----------------------------
INSERT INTO `shangpinfenlei` VALUES (1, '2025-04-06 15:12:38', '商品分类1');
INSERT INTO `shangpinfenlei` VALUES (2, '2025-04-06 15:12:38', '商品分类2');
INSERT INTO `shangpinfenlei` VALUES (3, '2025-04-06 15:12:38', '商品分类3');
INSERT INTO `shangpinfenlei` VALUES (4, '2025-04-06 15:12:38', '商品分类4');
INSERT INTO `shangpinfenlei` VALUES (5, '2025-04-06 15:12:38', '商品分类5');
INSERT INTO `shangpinfenlei` VALUES (6, '2025-04-06 15:12:38', '商品分类6');
INSERT INTO `shangpinfenlei` VALUES (7, '2025-04-06 15:12:38', '商品分类7');
INSERT INTO `shangpinfenlei` VALUES (8, '2025-04-06 15:12:38', '商品分类8');
INSERT INTO `shangpinfenlei` VALUES (9, '2025-04-06 15:12:38', '商品分类9');
INSERT INTO `shangpinfenlei` VALUES (10, '2025-04-06 15:12:38', '商品分类10');

-- ----------------------------
-- Table structure for shangpinxinxi
-- ----------------------------
DROP TABLE IF EXISTS `shangpinxinxi`;
CREATE TABLE `shangpinxinxi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangpinbianhao` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品编号',
  `shangpinmingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `shangpintupian` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品图片',
  `shangpinxiangqing` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品详情',
  `guige` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规格',
  `baozhiqi` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '保质期',
  `shangpinfenlei` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品分类',
  `shangjia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `storeup_number` int(11) NULL DEFAULT NULL COMMENT '收藏数',
  `discuss_number` int(11) NULL DEFAULT NULL COMMENT '评论数',
  `onelimittimes` int(11) NULL DEFAULT NULL COMMENT '单限',
  `alllimittimes` int(11) NULL DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `shangpinbianhao`(`shangpinbianhao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangpinxinxi
-- ----------------------------
INSERT INTO `shangpinxinxi` VALUES (1, '2025-04-06 15:12:38', '1111111111', '商品名称1', 'file/shangpinxinxiShangpintupian1.jpg,file/shangpinxinxiShangpintupian2.jpg,file/shangpinxinxiShangpintupian3.jpg', '商品详情1', '规格1', '保质期1', '商品分类1', '商家1', 99.9, 1, 1, 1, 97);
INSERT INTO `shangpinxinxi` VALUES (2, '2025-04-06 15:12:38', '2222222222', '商品名称2', 'file/shangpinxinxiShangpintupian2.jpg,file/shangpinxinxiShangpintupian3.jpg,file/shangpinxinxiShangpintupian4.jpg', '商品详情2', '规格2', '保质期2', '商品分类2', '商家2', 99.9, 2, 2, 2, 99);
INSERT INTO `shangpinxinxi` VALUES (3, '2025-04-06 15:12:38', '3333333333', '商品名称3', 'file/shangpinxinxiShangpintupian3.jpg,file/shangpinxinxiShangpintupian4.jpg,file/shangpinxinxiShangpintupian5.jpg', '商品详情3', '规格3', '保质期3', '商品分类3', '商家3', 99.9, 3, 3, 3, 99);
INSERT INTO `shangpinxinxi` VALUES (4, '2025-04-06 15:12:38', '4444444444', '商品名称4', 'file/shangpinxinxiShangpintupian4.jpg,file/shangpinxinxiShangpintupian5.jpg,file/shangpinxinxiShangpintupian6.jpg', '商品详情4', '规格4', '保质期4', '商品分类4', '商家4', 99.9, 4, 4, 4, 99);
INSERT INTO `shangpinxinxi` VALUES (5, '2025-04-06 15:12:38', '5555555555', '商品名称5', 'file/shangpinxinxiShangpintupian5.jpg,file/shangpinxinxiShangpintupian6.jpg,file/shangpinxinxiShangpintupian7.jpg', '商品详情5', '规格5', '保质期5', '商品分类5', '商家5', 99.9, 5, 5, 5, 99);
INSERT INTO `shangpinxinxi` VALUES (6, '2025-04-06 15:12:38', '6666666666', '商品名称6', 'file/shangpinxinxiShangpintupian6.jpg,file/shangpinxinxiShangpintupian7.jpg,file/shangpinxinxiShangpintupian8.jpg', '商品详情6', '规格6', '保质期6', '商品分类6', '商家6', 99.9, 6, 6, 6, 99);
INSERT INTO `shangpinxinxi` VALUES (7, '2025-04-06 15:12:38', '7777777777', '商品名称7', 'file/shangpinxinxiShangpintupian7.jpg,file/shangpinxinxiShangpintupian8.jpg,file/shangpinxinxiShangpintupian9.jpg', '商品详情7', '规格7', '保质期7', '商品分类7', '商家7', 99.9, 7, 7, 7, 99);
INSERT INTO `shangpinxinxi` VALUES (8, '2025-04-06 15:12:38', '8888888888', '商品名称8', 'file/shangpinxinxiShangpintupian8.jpg,file/shangpinxinxiShangpintupian9.jpg,file/shangpinxinxiShangpintupian10.jpg', '商品详情8', '规格8', '保质期8', '商品分类8', '商家8', 99.9, 8, 8, 8, 99);
INSERT INTO `shangpinxinxi` VALUES (9, '2025-04-06 15:12:38', '9999999999', '商品名称9', 'file/shangpinxinxiShangpintupian9.jpg,file/shangpinxinxiShangpintupian10.jpg,file/shangpinxinxiShangpintupian11.jpg', '商品详情9', '规格9', '保质期9', '商品分类9', '商家9', 99.9, 9, 9, 9, 99);
INSERT INTO `shangpinxinxi` VALUES (10, '2025-04-06 15:12:38', '11111111110', '商品名称10', 'file/shangpinxinxiShangpintupian10.jpg,file/shangpinxinxiShangpintupian11.jpg,file/shangpinxinxiShangpintupian12.jpg', '商品详情10', '规格10', '保质期10', '商品分类10', '商家10', 99.9, 10, 10, 10, 99);
INSERT INTO `shangpinxinxi` VALUES (11, '2026-03-30 09:34:11', '1774834432644', '123', 'file/1774834915126.jpg', '<p>123</p>', '123', '123', '商品分类9', '商家1', 111, NULL, NULL, 10, 100);

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NULL DEFAULT NULL COMMENT 'refid',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '我的收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storeup
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 1, 'admin', 'users', '管理员', 'r6jnrjfc9oq2if64ip0r6kfzncq4cwa8', '2026-03-30 09:31:39', '2026-03-30 21:42:54');
INSERT INTO `token` VALUES (2, 131, '商家1', 'shangjia', '商家', '7g0rw0dralzvvonq5hmb93qsxjmfc90m', '2026-03-30 09:33:34', '2026-03-30 21:14:42');
INSERT INTO `token` VALUES (3, 51, '用户1', 'yonghu', '用户', '0m38b7tn61rjsbbg6tl0a2yz5cgwmro3', '2026-03-30 09:36:50', '2026-03-30 20:34:37');
INSERT INTO `token` VALUES (4, 53, '用户3', 'yonghu', '用户', 'x1ipd7oszg1mxlrjcqxamaunkelsipyd', '2026-03-30 09:43:42', '2026-03-30 17:50:12');
INSERT INTO `token` VALUES (5, 52, '用户2', 'yonghu', '用户', 'ky1u6j84ki5wfnvjfqxvr9mlvj1vvc30', '2026-03-30 09:49:40', '2026-03-30 17:31:54');

-- ----------------------------
-- Table structure for tuangou_activity
-- ----------------------------
DROP TABLE IF EXISTS `tuangou_activity`;
CREATE TABLE `tuangou_activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `goodsid` bigint(20) NOT NULL COMMENT '商品 ID',
  `goodsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品图片',
  `groupono` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '拼团编号',
  `groupprice` double NULL DEFAULT NULL COMMENT '团购价',
  `grouppeople` int(11) NOT NULL COMMENT '要求拼团人数',
  `curpeople` int(11) NOT NULL DEFAULT 1 COMMENT '当前拼团人数',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '拼团中' COMMENT '拼团状态 (拼团中/已拼成/已失败)',
  `leaderid` bigint(20) NOT NULL COMMENT '团长用户 ID',
  `leadername` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '团长姓名',
  `endtime` datetime NULL DEFAULT NULL COMMENT '拼团结束时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `groupono`(`groupono` ASC) USING BTREE,
  INDEX `goodsid_status`(`goodsid` ASC, `status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '拼团活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tuangou_activity
-- ----------------------------
INSERT INTO `tuangou_activity` VALUES (18, '2026-03-30 15:56:15', 11, '123', 'file/1774834888839.jpg', '20260330155614955642', 66, 3, 0, '已结束', 53, '姓名3', NULL);
INSERT INTO `tuangou_activity` VALUES (19, '2026-03-30 16:24:05', 11, '123', 'file/1774834888839.jpg', '20260330162405607810', 66, 3, 3, '已拼成', 51, '姓名1', NULL);
INSERT INTO `tuangou_activity` VALUES (20, '2026-03-30 16:31:43', 2, '商品名称2', 'file/tuangoushangpinShangpintupian2.jpg', '20260330163143130725', 9.9, 2, 2, '已拼成', 53, '姓名3', NULL);
INSERT INTO `tuangou_activity` VALUES (21, '2026-03-30 16:49:11', 11, '123', 'file/1774834888839.jpg', '20260330164911938465', 66, 3, 2, '拼团中', 52, '姓名2', NULL);
INSERT INTO `tuangou_activity` VALUES (22, '2026-03-30 16:49:56', 11, '123', 'file/1774834888839.jpg', '20260330164956963658', 66, 3, 1, '拼团中', 51, '姓名1', NULL);
INSERT INTO `tuangou_activity` VALUES (23, '2026-03-30 20:02:57', 11, '123', 'file/1774834888839.jpg', '20260330200257056857', 66, 3, 0, '已结束', 51, '姓名1', NULL);

-- ----------------------------
-- Table structure for tuangoushangpin
-- ----------------------------
DROP TABLE IF EXISTS `tuangoushangpin`;
CREATE TABLE `tuangoushangpin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangpinbianhao` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品编号',
  `shangpinmingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `shangpintupian` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品图片',
  `shangpinxiangqing` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品详情',
  `guige` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规格',
  `baozhiqi` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '保质期',
  `shangpinfenlei` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品分类',
  `shangjia` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `storeup_number` int(11) NULL DEFAULT 0 COMMENT '收藏数',
  `discuss_number` int(11) NULL DEFAULT 0 COMMENT '评论数',
  `onelimittimes` int(11) NULL DEFAULT -1 COMMENT '单限',
  `alllimittimes` int(11) NULL DEFAULT -1 COMMENT '库存',
  `groupprice` double NULL DEFAULT 0 COMMENT '团购价',
  `grouppeople` int(11) NULL DEFAULT 0 COMMENT '拼团人数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `shangpinbianhao`(`shangpinbianhao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '团购商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tuangoushangpin
-- ----------------------------
INSERT INTO `tuangoushangpin` VALUES (1, '2025-04-06 15:12:38', '1111111111', '商品名称1', 'file/tuangoushangpinShangpintupian1.jpg,file/tuangoushangpinShangpintupian2.jpg,file/tuangoushangpinShangpintupian3.jpg', '商品详情1', '规格1', '保质期1', '商品分类1', '商家1', 99.9, 1, 1, 1, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (2, '2025-04-06 15:12:38', '2222222222', '商品名称2', 'file/tuangoushangpinShangpintupian2.jpg,file/tuangoushangpinShangpintupian3.jpg,file/tuangoushangpinShangpintupian4.jpg', '商品详情2', '规格2', '保质期2', '商品分类2', '商家2', 99.9, 2, 2, 2, 96, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (3, '2025-04-06 15:12:38', '3333333333', '商品名称3', 'file/tuangoushangpinShangpintupian3.jpg,file/tuangoushangpinShangpintupian4.jpg,file/tuangoushangpinShangpintupian5.jpg', '商品详情3', '规格3', '保质期3', '商品分类3', '商家3', 99.9, 3, 3, 3, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (4, '2025-04-06 15:12:38', '4444444444', '商品名称4', 'file/tuangoushangpinShangpintupian4.jpg,file/tuangoushangpinShangpintupian5.jpg,file/tuangoushangpinShangpintupian6.jpg', '商品详情4', '规格4', '保质期4', '商品分类4', '商家4', 99.9, 4, 4, 4, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (5, '2025-04-06 15:12:38', '5555555555', '商品名称5', 'file/tuangoushangpinShangpintupian5.jpg,file/tuangoushangpinShangpintupian6.jpg,file/tuangoushangpinShangpintupian7.jpg', '商品详情5', '规格5', '保质期5', '商品分类5', '商家5', 99.9, 5, 5, 5, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (6, '2025-04-06 15:12:38', '6666666666', '商品名称6', 'file/tuangoushangpinShangpintupian6.jpg,file/tuangoushangpinShangpintupian7.jpg,file/tuangoushangpinShangpintupian8.jpg', '商品详情6', '规格6', '保质期6', '商品分类6', '商家6', 99.9, 6, 6, 6, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (7, '2025-04-06 15:12:38', '7777777777', '商品名称7', 'file/tuangoushangpinShangpintupian7.jpg,file/tuangoushangpinShangpintupian8.jpg,file/tuangoushangpinShangpintupian9.jpg', '商品详情7', '规格7', '保质期7', '商品分类7', '商家7', 99.9, 7, 7, 7, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (8, '2025-04-06 15:12:38', '8888888888', '商品名称8', 'file/tuangoushangpinShangpintupian8.jpg,file/tuangoushangpinShangpintupian9.jpg,file/tuangoushangpinShangpintupian10.jpg', '商品详情8', '规格8', '保质期8', '商品分类8', '商家8', 99.9, 8, 8, 8, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (9, '2025-04-06 15:12:38', '9999999999', '商品名称9', 'file/tuangoushangpinShangpintupian9.jpg,file/tuangoushangpinShangpintupian10.jpg,file/tuangoushangpinShangpintupian11.jpg', '商品详情9', '规格9', '保质期9', '商品分类9', '商家9', 99.9, 9, 9, 9, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (10, '2025-04-06 15:12:38', '11111111110', '商品名称10', 'file/tuangoushangpinShangpintupian10.jpg,file/tuangoushangpinShangpintupian11.jpg,file/tuangoushangpinShangpintupian12.jpg', '商品详情10', '规格10', '保质期10', '商品分类10', '商家10', 99.9, 10, 10, 10, 99, 9.9, 2);
INSERT INTO `tuangoushangpin` VALUES (11, '2026-03-30 09:36:10', '1774834432644', '123', 'file/1774834888839.jpg', '<p>123</p>', '123', '123', '商品分类9', '商家1', 111, 0, 1, 10, 64, 66, 3);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '管理员' COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '2025-04-06 15:12:38', 'admin', 'admin', '管理员');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghu` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户',
  `mima` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingming` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `xingbie` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `touxiang` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '头像',
  `nianling` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `money` double NULL DEFAULT 0 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `yonghu`(`yonghu` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (51, '2025-04-06 15:12:38', '用户1', '123456', '姓名1', '男', 'file/yonghuTouxiang1.jpg', 1, 9999077);
INSERT INTO `yonghu` VALUES (52, '2025-04-06 15:12:38', '用户2', '123456', '姓名2', '男', 'file/yonghuTouxiang2.jpg', 2, 99999363.2);
INSERT INTO `yonghu` VALUES (53, '2025-04-06 15:12:38', '用户3', '123456', '姓名3', '男', 'file/yonghuTouxiang3.jpg', 3, 999551.3);
INSERT INTO `yonghu` VALUES (54, '2025-04-06 15:12:38', '用户4', '123456', '姓名4', '男', 'file/yonghuTouxiang4.jpg', 4, 200);
INSERT INTO `yonghu` VALUES (55, '2025-04-06 15:12:38', '用户5', '123456', '姓名5', '男', 'file/yonghuTouxiang5.jpg', 5, 200);
INSERT INTO `yonghu` VALUES (56, '2025-04-06 15:12:38', '用户6', '123456', '姓名6', '男', 'file/yonghuTouxiang6.jpg', 6, 200);
INSERT INTO `yonghu` VALUES (57, '2025-04-06 15:12:38', '用户7', '123456', '姓名7', '男', 'file/yonghuTouxiang7.jpg', 7, 200);
INSERT INTO `yonghu` VALUES (58, '2025-04-06 15:12:38', '用户8', '123456', '姓名8', '男', 'file/yonghuTouxiang8.jpg', 8, 200);
INSERT INTO `yonghu` VALUES (59, '2025-04-06 15:12:38', '用户9', '123456', '姓名9', '男', 'file/yonghuTouxiang9.jpg', 9, 200);
INSERT INTO `yonghu` VALUES (60, '2025-04-06 15:12:38', '用户10', '123456', '姓名10', '男', 'file/yonghuTouxiang10.jpg', 10, 200);

SET FOREIGN_KEY_CHECKS = 1;
