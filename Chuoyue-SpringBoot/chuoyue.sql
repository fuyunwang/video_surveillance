/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : chuoyue

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 04/11/2020 19:18:00
*/

DROP DATABASE IF EXISTS `chuoyue`;
create database `chuoyue` default character set utf8mb4 collate utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
USE `chuoyue`;

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
) ENGINE = MyISAM AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_acl
-- ----------------------------
INSERT INTO `tb_acl` VALUES (1, '超管和高管可以看到', '/chuoyue/hello/index/se', 'Admin', '超管和高管可以看到', 1, 1, '2019-04-03 21:29:50');
INSERT INTO `tb_acl` VALUES (2, '只有超管才能看到', '/chuoyue/hello/index/ad', 'Admin', '只有超管才能看到', 1, 0, '2019-04-03 21:32:58');
INSERT INTO `tb_acl` VALUES (3, '登陆之后就可以看到', '/chuoyue/hello/index/lo', 'Admin', '登陆之后就可以看到', 1, 2, '2019-04-03 21:48:08');
INSERT INTO `tb_acl` VALUES (20, NULL, '/chuoyue/hello/index/test1', NULL, NULL, NULL, NULL, '2020-10-19 20:44:30');
INSERT INTO `tb_acl` VALUES (5, '超管、中管、高管都可以看', '/chuoyue/hello/index/ju', 'Admin', '超管、中管、高管都可以看', 1, 1, '2019-04-03 21:52:53');
INSERT INTO `tb_acl` VALUES (21, NULL, '/chuoyue/tb-agent/**', 'Admin', NULL, 1, 1, '2020-10-19 20:44:28');
INSERT INTO `tb_acl` VALUES (22, NULL, '/chuoyue/tb-menu-info/**', NULL, NULL, 1, 1, '2020-10-19 20:44:25');
INSERT INTO `tb_acl` VALUES (23, NULL, '/chuoyue/tb-device/**', NULL, NULL, 1, 1, '2020-10-19 20:44:21');
INSERT INTO `tb_acl` VALUES (24, NULL, '/chuoyue/tb-department-solved/**', NULL, NULL, 1, 1, NULL);
INSERT INTO `tb_acl` VALUES (25, NULL, '/chuoyue/tb-department/**', NULL, NULL, 1, 1, NULL);

-- ----------------------------
-- Table structure for tb_agent
-- ----------------------------
DROP TABLE IF EXISTS `tb_agent`;
CREATE TABLE `tb_agent`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `agent_motto` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK87oi6qqndbhjr95l34sq1t7dv`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_agent
-- ----------------------------
INSERT INTO `tb_agent` VALUES (1, '1', 'beautifulsoup@163.com', '372330000007777663220', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower5.jpg', 'FuyunWang', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195200', 'SDNU', 0, 0, 1, '2020-10-20 09:55:44');
INSERT INTO `tb_agent` VALUES (9, '能工摩型,巧匠窃意', 'beautifulsoup@126.com', '372230000000000', 'http://qi2c9qbdt.hb-bkt.clouddn.com/snow1.jpg', 'wangxiaohao', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 1, 0, 2, '2020-11-01 09:55:44');
INSERT INTO `tb_agent` VALUES (10, '15', 'beautifulsoup@126.com', '372230000000000', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower2.jpg', 'wangshu', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 1, 0, 2, '2020-05-29 09:55:44');
INSERT INTO `tb_agent` VALUES (11, '0', 'beautifulsoup@126.com', '372230000000000', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'beautifulsoup', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 10, 0, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (12, '11', 'beautifulsoup@126.com', '372230000000000', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'gopher', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 9, 0, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (13, '10', 'beautifulsoup@126.com', '372230000000000', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'zhudunru', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 9, 1, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (14, '0', 'beautifulsoup@126.com', '372230000000000', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'libai', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195552', '山东师范大学', 1, 1, 2, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (15, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'wangbo', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (16, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'aaa号-1', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 15, 0, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (17, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'bbb号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (18, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'bbb号-1', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 17, 0, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (19, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'ccc号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (20, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'ccc号-1', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 19, 0, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (21, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'ddd号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (22, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'eee号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (23, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'ccc号-2', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 19, 0, 3, '2019-02-04 09:55:44');
INSERT INTO `tb_agent` VALUES (24, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'ccc号-3', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 19, 0, 3, '2018-06-17 09:55:44');
INSERT INTO `tb_agent` VALUES (25, '0', '1721693585@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'fff号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2019-03-26 09:55:44');
INSERT INTO `tb_agent` VALUES (26, '0', '2152521764@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower3.jpg', 'hhh号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2019-04-04 09:55:44');
INSERT INTO `tb_agent` VALUES (27, '0', '2152521764@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/flower1.jpg', 'mmm号', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 1, 1, 2, '2019-10-04 09:55:44');
INSERT INTO `tb_agent` VALUES (28, '你会的越多,你不会的越多', '2152521764@qq.com', '372330111198888', 'http://qi2c9qbdt.hb-bkt.clouddn.com/snow2.jpg', 'mmm号-1', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195555', '山东师范大学', 27, 0, 3, '2020-10-31 09:55:44');
INSERT INTO `tb_agent` VALUES (29, '万事起于忽微,量变引起质变', '2375872953@qq.com', NULL, 'http://qi2c9qbdt.hb-bkt.clouddn.com/avatar1.jpg', 'administrator', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '17864195301', NULL, NULL, 0, 2, '2020-11-04 09:55:44');
INSERT INTO `tb_agent` VALUES (30, '莫道君行早,更有早行人', '1836361620@qq.com', NULL, 'http://qi2c9qbdt.hb-bkt.clouddn.com/avatar2.jpg', 'BeautifulSoup2020', '$2a$10$Dm3mr0P5NBDP8E4Wl1sYQe/jbEhFhsYPp3OArMDYZ4V6FWBEl0m3i', '18954157220', NULL, NULL, 0, 4, '2020-11-02 09:55:44');

-- ----------------------------
-- Table structure for tb_agent_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_agent_role`;
CREATE TABLE `tb_agent_role`  (
  `agent_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_agent_role
-- ----------------------------
INSERT INTO `tb_agent_role` VALUES (29, 1);
INSERT INTO `tb_agent_role` VALUES (29, 2);
INSERT INTO `tb_agent_role` VALUES (29, 3);
INSERT INTO `tb_agent_role` VALUES (11, 2);
INSERT INTO `tb_agent_role` VALUES (11, 3);
INSERT INTO `tb_agent_role` VALUES (11, 1);

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department`  (
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
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES (1, '测试组1', '2020-11-05 23:50:01', '人员检测', '设备1', 'http://qi2c9qbdt.hb-bkt.clouddn.com/person_detect.mp4', 0, '17861423310', 'person');
INSERT INTO `tb_department` VALUES (2, '测试组2', '2020-11-04 19:01:14', '安全绳检测', '设备1', 'http://qi2c9qbdt.hb-bkt.clouddn.com/new_safety.mp4', 0, '18952437510', 'safety');
INSERT INTO `tb_department` VALUES (3, '测试组3', '2020-11-05 23:49:19', '吸烟检测', '设备1', 'http://qi2c9qbdt.hb-bkt.clouddn.com/napianhai.mp4', 0, '15552220331', 'smoking');
INSERT INTO `tb_department` VALUES (4, '测试组4', '2020-11-05 23:49:23', '打电话检测', '设备1', '', 0, '18913024586', 'phoning');
INSERT INTO `tb_department` VALUES (5, '测试组5', '2020-11-05 23:49:35', '电机检测', '设备1', '', 0, '19854157620', 'electric');
INSERT INTO `tb_department` VALUES (6, '测试组6', '2020-11-05 23:49:41', '火焰', '设备1', '', 0, '13422109987', 'flame');

-- ----------------------------
-- Table structure for tb_department_solved
-- ----------------------------
DROP TABLE IF EXISTS `tb_department_solved`;
CREATE TABLE `tb_department_solved`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `departmentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名',
  `alarmTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '报警时间',
  `incidentType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件类型',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `screenShot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '抓拍图',
  `status` int(0) NOT NULL DEFAULT 0 COMMENT '状态（0 未处理 1 已处理 3 误报警）',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `departmentId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `deviceName`(`deviceName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_department_solved
-- ----------------------------

-- ----------------------------
-- Table structure for tb_device
-- ----------------------------
DROP TABLE IF EXISTS `tb_device`;
CREATE TABLE `tb_device`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `deviceUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备用户名',
  `deviceIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备IP地址',
  `RTSP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'RTSP端口',
  `gateway` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网关',
  `status` int(0) NOT NULL COMMENT '状态（0正常 1异常）',
  `departmentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `state` int(0) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `deviceNmae`(`deviceName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_device
-- ----------------------------
INSERT INTO `tb_device` VALUES (1, '人员检测设备', 'administrator', '192.168.1.100', '26101', '人员检测', 0, '测试组1', '门口', 0);
INSERT INTO `tb_device` VALUES (2, '安全绳检测设备', 'administrator', '192.168.1.101', '554', '安全绳检测', 0, '测试组2', '过道', 0);
INSERT INTO `tb_device` VALUES (3, '吸烟检测设备', 'administrator', '192.168.1.102', '554', '吸烟检测', 0, '测试组3', '楼梯', 0);
INSERT INTO `tb_device` VALUES (4, '打电话检测设备', 'administrator', '192.168.1.103', '554', '打电话检测', 0, '测试组4', '后墙', NULL);

-- ----------------------------
-- Table structure for tb_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_info`;
CREATE TABLE `tb_menu_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路径',
  `orders` int(0) NULL DEFAULT 1 COMMENT '顺序',
  `parent_id` int(0) NULL DEFAULT 0 COMMENT '父级id,为0表示为根父级',
  `auth_type` int(0) NULL DEFAULT 3 COMMENT '权限类型,0是只有超管才能看到,1是高管可以看到,2是中管可以看到,3是所有人都可以看到',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `redirect` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu_info
-- ----------------------------
INSERT INTO `tb_menu_info` VALUES (1, '首页展示', '/system/portal', 1, 0, 1, 'system/portal/index', 'noredirect');
INSERT INTO `tb_menu_info` VALUES (2, '报警配置', '/alert', 5, 0, 1, 'Layout', 'system/alert/index');
INSERT INTO `tb_menu_info` VALUES (3, '配置配置', '/settings', 4, 0, 1, 'Layout', 'noredirect');
INSERT INTO `tb_menu_info` VALUES (4, '数据统计', '/system', 7, 0, 1, 'Layout', 'noredirect');
INSERT INTO `tb_menu_info` VALUES (10, '数据报表3', 'chart/first', 3, 4, 3, 'system/chart/first/index', 'noredirect');
INSERT INTO `tb_menu_info` VALUES (11, '数据报表1', 'chart/second', 1, 4, 3, 'system/chart/second/index', 'noredirect');
INSERT INTO `tb_menu_info` VALUES (12, '数据报表2', 'chart/third', 2, 4, 3, 'system/chart/third/index', 'noredirect');
INSERT INTO `tb_menu_info` VALUES (13, '报警管理', 'alert', 1, 2, 3, 'system/alert/index', '');
INSERT INTO `tb_menu_info` VALUES (14, '配置管理', 'settings', 1, 3, 3, 'system/settings/index', '');
INSERT INTO `tb_menu_info` VALUES (15, '用户模块', '/user', 2, 0, 3, 'Layout', '');
INSERT INTO `tb_menu_info` VALUES (16, '用户管理', 'user', 1, 15, 3, 'system/user/index', '');
INSERT INTO `tb_menu_info` VALUES (19, '部门模块', '/department', 3, 0, 3, 'Layout', '');
INSERT INTO `tb_menu_info` VALUES (20, '部门管理', 'department', 1, 19, 3, 'system/department/index', '');
INSERT INTO `tb_menu_info` VALUES (21, '数据报表4', 'chart/fourth', 4, 4, 3, 'system/chart/fourth/index', '');
INSERT INTO `tb_menu_info` VALUES (22, '直播检测', '/live', 6, 0, 3, 'Layout', '');
INSERT INTO `tb_menu_info` VALUES (23, '直播检测', 'live', 1, 22, 3, 'system/live/index', '');

-- ----------------------------
-- Table structure for tb_menu_info_meta
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_info_meta`;
CREATE TABLE `tb_menu_info_meta`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `menu_id` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu_info_meta
-- ----------------------------
INSERT INTO `tb_menu_info_meta` VALUES (1, '算法配置', 'edit', 14);
INSERT INTO `tb_menu_info_meta` VALUES (2, '报警管理', 'documentation', 13);
INSERT INTO `tb_menu_info_meta` VALUES (3, '数据报表1', 'table', 11);
INSERT INTO `tb_menu_info_meta` VALUES (4, '数据报表2', 'table', 12);
INSERT INTO `tb_menu_info_meta` VALUES (5, '数据报表3', 'table', 10);
INSERT INTO `tb_menu_info_meta` VALUES (6, '数据统计', 'chart', 4);
INSERT INTO `tb_menu_info_meta` VALUES (7, '用户管理', 'user', 16);
INSERT INTO `tb_menu_info_meta` VALUES (9, '部门管理', 'dashboard', 20);
INSERT INTO `tb_menu_info_meta` VALUES (10, '数据报表4', 'table', 21);
INSERT INTO `tb_menu_info_meta` VALUES (11, '直播检测', 'component', 23);

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
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'Admin', '', 'admin', 1, 0, '2019-04-03 19:57:05');
INSERT INTO `tb_role` VALUES (2, 'Admin', '', 'senior', 1, 1, '2019-04-04 10:20:56');
INSERT INTO `tb_role` VALUES (3, 'Admin', '', 'junior', 1, 2, '2019-04-04 10:23:21');
INSERT INTO `tb_role` VALUES (4, NULL, NULL, 'test1', NULL, NULL, NULL);

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
INSERT INTO `tb_role_acl` VALUES (1, 1);
INSERT INTO `tb_role_acl` VALUES (1, 2);
INSERT INTO `tb_role_acl` VALUES (1, 3);
INSERT INTO `tb_role_acl` VALUES (3, 5);
INSERT INTO `tb_role_acl` VALUES (1, 5);
INSERT INTO `tb_role_acl` VALUES (2, 1);
INSERT INTO `tb_role_acl` VALUES (2, 3);
INSERT INTO `tb_role_acl` VALUES (2, 5);
INSERT INTO `tb_role_acl` VALUES (3, 3);
INSERT INTO `tb_role_acl` VALUES (1, 20);
INSERT INTO `tb_role_acl` VALUES (2, 20);
INSERT INTO `tb_role_acl` VALUES (1, 21);
INSERT INTO `tb_role_acl` VALUES (2, 21);
INSERT INTO `tb_role_acl` VALUES (1, 22);
INSERT INTO `tb_role_acl` VALUES (2, 22);
INSERT INTO `tb_role_acl` VALUES (1, 23);
INSERT INTO `tb_role_acl` VALUES (2, 23);
INSERT INTO `tb_role_acl` VALUES (1, 24);
INSERT INTO `tb_role_acl` VALUES (2, 24);
INSERT INTO `tb_role_acl` VALUES (1, 25);
INSERT INTO `tb_role_acl` VALUES (2, 25);

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
