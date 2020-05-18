/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : rent005

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 18/05/2020 08:17:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `article_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公告id',
  `article_title` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告标题',
  `article_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告内容',
  `send_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_back
-- ----------------------------
DROP TABLE IF EXISTS `tb_back`;
CREATE TABLE `tb_back`  (
  `back_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '反馈id',
  `return_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '返还单id',
  `ad_type` int(1) NULL DEFAULT NULL COMMENT '反馈类别 建议/故障',
  `more_info` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '如果为车辆故障 则为车辆车牌',
  `advice` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '反馈内容',
  `user_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '反馈人 nick_name',
  `is_solve` int(1) NULL DEFAULT NULL COMMENT '是否解决',
  `solve_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '解决人 nick_name',
  `date_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`back_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_car
-- ----------------------------
DROP TABLE IF EXISTS `tb_car`;
CREATE TABLE `tb_car`  (
  `car_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '汽车id',
  `car_num` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车牌照',
  `car_type` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车类型',
  `buy_price` double(10, 2) NULL DEFAULT NULL COMMENT '汽车市值',
  `rent_price` double(10, 2) NULL DEFAULT NULL COMMENT '租用价格',
  `deposit_price` double(10, 2) NULL DEFAULT NULL COMMENT '押金价格',
  `rent_status` int(1) NULL DEFAULT NULL COMMENT '租用状态',
  `car_color` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车颜色',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车描述',
  `car_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车图片',
  `rent_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '出租人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `is_repair` int(1) NULL DEFAULT NULL COMMENT '是否需要维护 1是0否',
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_history`;
CREATE TABLE `tb_history`  (
  `his_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '历史id',
  `visit_count` int(255) NULL DEFAULT NULL COMMENT '访问数',
  PRIMARY KEY (`his_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `log_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '日志id',
  `log_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志类型 1登录/2删除',
  `log_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
  `mes_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息id',
  `mes_type` int(1) NULL DEFAULT NULL COMMENT '信息类别',
  `mes_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信息内容',
  `mes_from` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信息来源 系统/XX用户',
  `mes_to` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信息送达',
  `mes_status` int(1) NULL DEFAULT NULL COMMENT '信息状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '生成时间',
  PRIMARY KEY (`mes_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `nick_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '租用者',
  `car_num` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车车牌',
  `car_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '车辆id',
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户id',
  `day_count` int(10) NULL DEFAULT NULL COMMENT '租用天数',
  `order_price` double(10, 2) NULL DEFAULT NULL COMMENT '租用价格',
  `order_deposit` double(10, 2) NULL DEFAULT NULL COMMENT '押金金额',
  `total_price` double(10, 2) NULL DEFAULT NULL COMMENT '预计金额',
  `order_status` int(1) NULL DEFAULT NULL COMMENT '是否归还 已还1/未还0',
  `is_over` int(1) NULL DEFAULT NULL COMMENT '是否逾期 正常0/逾期1',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '租借时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '预还时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `return_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '返单id',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `percode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `spread` int(11) NULL DEFAULT NULL,
  `available` int(11) NULL DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 391 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_return
-- ----------------------------
DROP TABLE IF EXISTS `tb_return`;
CREATE TABLE `tb_return`  (
  `return_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '归还id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单id',
  `car_num` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '汽车车牌',
  `nick_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '归还人昵称',
  `estimated_price` double(10, 2) NULL DEFAULT NULL COMMENT '预付价格',
  `actual_price` double(10, 2) NULL DEFAULT NULL COMMENT '实际支付',
  `more_info` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附加说明 逾期/未逾期附加金额',
  `ad_type` int(1) NULL DEFAULT NULL COMMENT '反馈类别：汽车/用户',
  `advice` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '反馈内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/归还时间',
  PRIMARY KEY (`return_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`pid`, `rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user`  (
  `rid` int(15) NOT NULL,
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uid`, `rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `type_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `type_content` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `user_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录密码',
  `nick_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '称呼',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别 0-女,1-男',
  `address` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在地',
  `phone` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱地址',
  `type` int(1) NULL DEFAULT NULL COMMENT '类别 1-超级管理员,2-系统用户',
  `img` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `available` int(1) NULL DEFAULT NULL COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_status` int(1) NULL DEFAULT 0 COMMENT '登录状态 1在线 0为不在线',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
