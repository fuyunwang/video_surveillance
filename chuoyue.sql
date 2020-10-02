/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : video_surveillance

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 28/09/2020 20:47:02
*/
DROP DATABASE IF EXISTS `video_surveillance`;
create database `video_surveillance` default character set utf8mb4 collate utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
USE `video_surveillance`;
-- ----------------------------
-- Table structure for algorithm
-- ----------------------------
DROP TABLE IF EXISTS `algorithm`;
CREATE TABLE `algorithm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `algorithmName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '算法名称',
  `startTime` datetime(0) NOT NULL COMMENT '算法开始时间',
  `stopTime` datetime(0) NOT NULL COMMENT '算法结束时间',
  `alarmInterval` int(0) NOT NULL COMMENT '报警间隔(秒)',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `algorithm_ibfk_1`(`deviceName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of algorithm
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `departmentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名',
  `alarmTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '报警时间',
  `incidentType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件类型',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `screenShot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '抓拍图',
  `status` int(0) NOT NULL COMMENT '状态（0 未处理 1 已处理 3 误报警）',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `deviceName`(`deviceName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '测试组', '2020-09-26 19:03:22', '火焰', '工厂1', 'http://qh9ik3frs.hb-bkt.clouddn.com/fabien-bellanger--s4f4gF6EHY-unsplash.jpg', 0, '', '');
INSERT INTO `department` VALUES (2, '测试组', '2020-09-26 20:49:46', '火焰', '工厂1', 'http://qh9ik3frs.hb-bkt.clouddn.com/fabien-bellanger--s4f4gF6EHY-unsplash.jpg', 0, '', '');
INSERT INTO `department` VALUES (3, '测试组', '2020-09-26 20:15:00', '火焰', '工厂1', 'http://qh9ik3frs.hb-bkt.clouddn.com/photo-1597711002271-db32de5ff02b.jpg', 0, '', '');
INSERT INTO `department` VALUES (4, '测试组', '2020-09-26 20:15:28', '火焰', '工厂1', 'http://qh9ik3frs.hb-bkt.clouddn.com/photo-1597750721050-f29f1f1eb2c7.jpg\r\n', 0, '', '');
INSERT INTO `department` VALUES (5, '测试组', '2020-09-26 19:03:25', '火焰', '工厂1', 'http://qh9ik3frs.hb-bkt.clouddn.com/fabien-bellanger--s4f4gF6EHY-unsplash.jpg', 0, '', '');
INSERT INTO `department` VALUES (6, '测试组', '2020-09-26 19:03:26', '火焰', '工厂1', 'http://qh9ik3frs.hb-bkt.clouddn.com/fabien-bellanger--s4f4gF6EHY-unsplash.jpg', 0, '', '');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `deviceUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备用户名',
  `deviceIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备IP地址',
  `RTSP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'RTSP端口',
  `gateway` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网关',
  `status` int(0) NOT NULL COMMENT '状态（0正常 1异常）',
  `departmentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `deviceNmae`(`deviceName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1, '工厂1', 'admin', '192.168.1.110', '26101', 'NANO开发测试', 0, '测试组', '门口');
INSERT INTO `device` VALUES (2, '灰色摄像机', 'admin', '192.168.1.64', '554', 'NANO开发测试', 0, '测试组', '饮水机QQ');
INSERT INTO `device` VALUES (3, '白色摄像头', 'admin', '192.168.1.65', '554', 'NANO', 0, '中科鸿云', '门口');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `employeeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员',
  `phoneNumber` int(0) NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for menu_info
-- ----------------------------
DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `menu_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路径',
  `orders` int(0) NULL DEFAULT 1 COMMENT '顺序',
  `parent_id` int(0) NULL DEFAULT 0 COMMENT '父级id,为0表示为根父级',
  `auth_type` int(0) NULL DEFAULT 3 COMMENT '权限类型,0是只有超管才能看到,1是高管可以看到,2是中管可以看到,3是所有人都可以看到',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_info
-- ----------------------------
INSERT INTO `menu_info` VALUES (1, '首页展示', 'portal/lo', 1, 0, 1);
INSERT INTO `menu_info` VALUES (2, '报警管理', 'alert/lo', 2, 0, 1);
INSERT INTO `menu_info` VALUES (3, '配置管理', 'settings/lo', 3, 0, 1);
INSERT INTO `menu_info` VALUES (4, '系统管理', 'system/lo', 4, 0, 1);
INSERT INTO `menu_info` VALUES (5, '首页展示1', 'portal/first', 1, 1, 3);
INSERT INTO `menu_info` VALUES (6, '首页展示2', 'portal/second', 2, 1, 3);
INSERT INTO `menu_info` VALUES (7, '首页展示11', 'portal/first/first', 1, 5, 3);
INSERT INTO `menu_info` VALUES (8, '报警管理1', 'alert/first', 1, 2, 3);
INSERT INTO `menu_info` VALUES (9, '配置管理1', 'settings/first', 1, 3, 3);
INSERT INTO `menu_info` VALUES (10, '系统管理1', 'system/first', 1, 4, 3);

-- ----------------------------
-- Table structure for tb_acl
-- ----------------------------
DROP TABLE IF EXISTS `tb_acl`;
CREATE TABLE `tb_acl`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `acl_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acl_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operator` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acl_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_acl
-- ----------------------------
INSERT INTO `tb_acl` VALUES (24, '获取设备列表', '/chuoyue/device/**', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_acl` VALUES (5, '测试权限拦截', '/chuoyue/hello/index/**', 'Admin', '所有人能够请求', 1, 1, '2019-04-03 21:52:53');
INSERT INTO `tb_acl` VALUES (21, '获取菜单列表', '/chuoyue/menus/**', 'Admin', '所有人能够请求', 1, 1, '2020-09-26 09:02:51');
INSERT INTO `tb_acl` VALUES (22, '获取菜单列表', '/chuoyue/menus/**', 'Admin', '所有人能够请求', 1, 1, '2020-09-26 20:36:48');
INSERT INTO `tb_acl` VALUES (23, '获取报警管理信息', '/chuoyue/department/**', 'Admin', '所有人都能请求', 1, 1, '2020-09-26 20:36:50');

-- ----------------------------
-- Table structure for tb_agent
-- ----------------------------
DROP TABLE IF EXISTS `tb_agent`;
CREATE TABLE `tb_agent`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `agent_achieve` int(0) NULL DEFAULT NULL,
  `agent_email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_idcard` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_idcard_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_school` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK87oi6qqndbhjr95l34sq1t7dv`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_agent
-- ----------------------------
INSERT INTO `tb_agent` VALUES (1, 2, 'beautifulsoup@163.com', '372330000007777663220', 'http://47.95.244.237:8888/driving/M00/00/00/111', 'FuyunWang', 'e10adc3949ba59abbe56e057f20f883e', '17864195200', 'SDNU', 0, 1, 1);
INSERT INTO `tb_agent` VALUES (9, 6, 'beautifulsoup@126.com', '372230000000000', 'http://www.aa.jpg', 'wangxiaohao', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (10, 15, 'beautifulsoup@126.com', '372230000000000', 'http://www.aa.jpg', 'wangshu', '670b14728ad9902aecba32e22fa4f6bd', '17864195552', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (11, 0, 'beautifulsoup@126.com', '372230000000000', 'http://www.aa.jpg', 'beautifulsoup', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 10, 1, 3);
INSERT INTO `tb_agent` VALUES (12, 11, 'beautifulsoup@126.com', '372230000000000', 'http://www.aa.jpg', 'gopher', '670b14728ad9902aecba32e22fa4f6bd', '17864195552', '山东师范大学', 9, 0, 3);
INSERT INTO `tb_agent` VALUES (13, 10, 'beautifulsoup@126.com', '372230000000000', 'http://www.aa.jpg', 'zhudunru', '670b14728ad9902aecba32e22fa4f6bd', '17864195552', '山东师范大学', 9, 1, 3);
INSERT INTO `tb_agent` VALUES (14, 0, 'beautifulsoup@126.com', '372230000000000', 'http://www.aa.jpg', 'libai', '670b14728ad9902aecba32e22fa4f6bd', '17864195552', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (15, 0, 'http://goudao.png', '372330111198888', '6669.5', 'wangbo', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (16, 0, 'http://goudao.png', '372330111198888', '6669.5', 'aaa号-1', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 15, 0, 3);
INSERT INTO `tb_agent` VALUES (17, 0, 'http://goudao.png', '372330111198888', '6669.5', 'bbb号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (18, 0, 'http://goudao.png', '372330111198888', '6669.5', 'bbb号-1', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 17, 0, 3);
INSERT INTO `tb_agent` VALUES (19, 0, 'http://goudao.png', '372330111198888', '6669.5', 'ccc号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (20, 0, 'http://goudao.png', '372330111198888', '6669.5', 'ccc号-1', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 19, 0, 3);
INSERT INTO `tb_agent` VALUES (21, 0, 'http://goudao.png', '372330111198888', '6669.5', 'ddd号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (22, 0, 'http://goudao.png', '372330111198888', '6669.5', 'eee号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (23, 0, 'http://goudao.png', '372330111198888', '6669.5', 'ccc号-2', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 19, 0, 3);
INSERT INTO `tb_agent` VALUES (24, 0, 'http://goudao.png', '372330111198888', '6669.5', 'ccc号-3', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 19, 0, 3);
INSERT INTO `tb_agent` VALUES (25, 0, 'http://goudao.png', '372330111198888', '6669.5', 'fff号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (26, 0, 'http://goudao.png', '372330111198888', '6669.5', 'hhh号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (27, 0, 'http://goudao.png', '372330111198888', '6669.5', 'mmm号', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 1, 1, 2);
INSERT INTO `tb_agent` VALUES (28, 0, 'http://goudao.png', '372330111198888', '6669.5', 'mmm号-1', '670b14728ad9902aecba32e22fa4f6bd', '17864195555', '山东师范大学', 27, 0, 3);
INSERT INTO `tb_agent` VALUES (29, NULL, NULL, NULL, NULL, 'administrator', '$2a$10$BUgxefACcsmADKMy1kyFHe7FIYaZfoYjoqUpx96IharVkqVOeHbMC', NULL, NULL, NULL, NULL, 1);
INSERT INTO `tb_agent` VALUES (30, NULL, NULL, NULL, NULL, 'lisi', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', NULL, NULL, NULL, NULL, 4);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `operator` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'Admin', '超管', 'administrator', 1, 10, '2019-04-03 19:57:05');
INSERT INTO `tb_role` VALUES (2, 'Admin', '高管', 'senior', 1, 5, '2019-04-04 10:20:56');
INSERT INTO `tb_role` VALUES (3, 'Admin', '中管', 'junior', 1, 3, '2019-04-04 10:23:21');
INSERT INTO `tb_role` VALUES (5, 'Admin', '用户', 'user', 1, 1, '2020-09-26 09:51:17');

-- ----------------------------
-- Table structure for tb_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_acl`;
CREATE TABLE `tb_role_acl`  (
  `role_id` int(0) NOT NULL,
  `acl_id` int(0) NOT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tb_role_acl
-- ----------------------------
INSERT INTO `tb_role_acl` VALUES (1, 2);
INSERT INTO `tb_role_acl` VALUES (2, 1);
INSERT INTO `tb_role_acl` VALUES (1, 1);
INSERT INTO `tb_role_acl` VALUES (3, 5);
INSERT INTO `tb_role_acl` VALUES (3, 5);
INSERT INTO `tb_role_acl` VALUES (1, 24);
INSERT INTO `tb_role_acl` VALUES (2, 24);
INSERT INTO `tb_role_acl` VALUES (2, 5);
INSERT INTO `tb_role_acl` VALUES (3, 24);
INSERT INTO `tb_role_acl` VALUES (1, 21);
INSERT INTO `tb_role_acl` VALUES (2, 21);
INSERT INTO `tb_role_acl` VALUES (3, 21);
INSERT INTO `tb_role_acl` VALUES (1, 22);
INSERT INTO `tb_role_acl` VALUES (2, 22);
INSERT INTO `tb_role_acl` VALUES (3, 22);
INSERT INTO `tb_role_acl` VALUES (1, 23);
INSERT INTO `tb_role_acl` VALUES (2, 23);
INSERT INTO `tb_role_acl` VALUES (3, 23);
INSERT INTO `tb_role_acl` VALUES (1, 5);

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `operator` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `student_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_price` decimal(19, 2) NULL DEFAULT NULL,
  `student_school` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (6, 'Admin', 1, '333656665', 'http://www.naodi.png', '5号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 14:46:24');
INSERT INTO `tb_student` VALUES (7, 'Admin', 1, '333656665', 'http://www.naodi.png', '4号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 14:51:17');
INSERT INTO `tb_student` VALUES (8, '2-1号代理', 1, '333656665', 'http://www.naodi.png', '2-1-1号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 14:54:35');
INSERT INTO `tb_student` VALUES (9, '1-2号代理', 1, '333656665', 'http://www.naodi.png', '1-2-1号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:06:32');
INSERT INTO `tb_student` VALUES (10, '1-2号代理', 1, '333656665', 'http://www.naodi.png', '1-2-1-1号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:08:10');
INSERT INTO `tb_student` VALUES (11, '1号代理', 0, '333656665', 'http://www.naodi.png', '1-3号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:12:43');
INSERT INTO `tb_student` VALUES (12, '1号代理', 1, '333656665', 'http://www.naodi.png', '1-4号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:12:52');
INSERT INTO `tb_student` VALUES (13, '1-1号代理', 1, '333656665', 'http://www.naodi.png', '1-1-1号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:13:46');
INSERT INTO `tb_student` VALUES (14, '1-1号代理', 1, '333656665', 'http://www.naodi.png', '1-1-2号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:13:50');
INSERT INTO `tb_student` VALUES (15, '1-1号代理', 1, '333656665', 'http://www.naodi.png', '1-1-3号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 15:13:54');
INSERT INTO `tb_student` VALUES (16, '1-1号代理', 1, '333656665', 'http://www.naodi.png', '1-1-4号学员', '17864553321', NULL, '北京师范大学', '2019-04-05 22:15:44');
INSERT INTO `tb_student` VALUES (22, 'aaa号', 0, '372330111198888', 'http://goudao.png', 'aa李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-08 13:38:56');
INSERT INTO `tb_student` VALUES (23, 'fff号', 0, '372330111198888', 'http://goudao.png', 'asd李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-08 15:27:25');
INSERT INTO `tb_student` VALUES (21, '1-2号代理', 0, '372330111198888', 'http://goudao.png', '李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-05 18:36:14');
INSERT INTO `tb_student` VALUES (24, 'fff号', 0, '372330111198888', 'http://goudao.png', 'asfadd李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-08 15:27:40');
INSERT INTO `tb_student` VALUES (25, 'fff号', 0, '372330111198888', 'http://goudao.png', 'asfadfadsfasfdasd李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-08 15:27:42');
INSERT INTO `tb_student` VALUES (26, 'fff号', 0, '372330111198888', 'http://goudao.png', 'asfadfadsfasfasdfasfasfasfdasd李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-08 15:27:54');
INSERT INTO `tb_student` VALUES (27, 'hhh号', 0, '372330111198888', 'http://goudao.png', 'asfadfadsfdsafdasfasdfasfasfasfdasd李狗蛋', '17864195555', 6669.50, '山东师范大学', '2019-04-08 15:30:27');

SET FOREIGN_KEY_CHECKS = 1;
