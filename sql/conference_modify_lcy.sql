/*
 Navicat Premium Data Transfer

 Source Server         : admin
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 47.96.234.116:3306
 Source Schema         : conference

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001
glz
 Date: 29/11/2020 20:19:35
*/

drop database if exists conference;
create database conference;
use conference;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `admin_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `admin_account` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`admin_id`, `admin_account`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`admin_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conference
-- ----------------------------
DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference`  (
  `conference_id` int(11) NOT NULL AUTO_INCREMENT,
  `organizer_id` int(11) NULL DEFAULT NULL,
  `fleet_id` int(11) NULL DEFAULT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `conference_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `conference_start` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `conference_end` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `conference_location` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `conference_info` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`conference_id`) USING BTREE,
  INDEX `FK_organise_conference`(`organizer_id`) USING BTREE,
  INDEX `FK_r_pickup`(`fleet_id`) USING BTREE,
  INDEX `FK_r_putup`(`hotel_id`) USING BTREE,
  CONSTRAINT `FK_organise_conference` FOREIGN KEY (`organizer_id`) REFERENCES `organizer` (`organizer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_r_pickup` FOREIGN KEY (`fleet_id`) REFERENCES `fleet` (`fleet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_r_putup` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `driver_id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `car_number` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fleet_id` int(11) NULL DEFAULT NULL,
  `driver_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `driver_phone` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `is_assign` tinyint(1) NOT NULL,
  PRIMARY KEY (`driver_id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`car_number`, `driver_phone`) USING BTREE,
  INDEX `FK_r_dirver_fleet`(`fleet_id`) USING BTREE,
  CONSTRAINT `FK_r_dirver_fleet` FOREIGN KEY (`fleet_id`) REFERENCES `fleet` (`fleet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fleet
-- ----------------------------
DROP TABLE IF EXISTS `fleet`;
CREATE TABLE `fleet`  (
  `fleet_id` int(11) NOT NULL AUTO_INCREMENT,
  `fleet_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fleet_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fleet_phone` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`fleet_id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`fleet_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `hotel_location` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `hotel_phone` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `hotel_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `hotel_info` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`hotel_id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`hotel_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for join_conference
-- ----------------------------
DROP TABLE IF EXISTS `join_conference`;
CREATE TABLE `join_conference`  (
  `participant_id` int(11) NOT NULL,
  `conference_id` int(11) NOT NULL,
  `is_putup` tinyint(1) NOT NULL,
  `is_pickup` tinyint(1) NULL DEFAULT NULL,
  `to_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `return_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `train_info` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`participant_id`, `conference_id`) USING BTREE,
  INDEX `FK_join_conference2`(`conference_id`) USING BTREE,
  CONSTRAINT `FK_join_conference2` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for live_room
-- ----------------------------
DROP TABLE IF EXISTS `live_room`;
CREATE TABLE `live_room`  (
  `participate_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_id` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`participate_id`, `hotel_id`) USING BTREE,
  INDEX `FK_live_room2`(`hotel_id`) USING BTREE,
  CONSTRAINT `FK_live_room` FOREIGN KEY (`participate_id`) REFERENCES `participant` (`participate_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_live_room2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for organizer
-- ----------------------------
DROP TABLE IF EXISTS `organizer`;
CREATE TABLE `organizer`  (
  `organizer_id` int(11) NOT NULL AUTO_INCREMENT,
  `organizer_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `organizer_phone` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `organizer_unit` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `organizer_email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`organizer_id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`organizer_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for participant
-- ----------------------------
DROP TABLE IF EXISTS `participant`;
CREATE TABLE `participant`  (
  `participate_id` int(11) NOT NULL AUTO_INCREMENT,
  `participate_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `participate_jobTitle` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `participate_workUnit` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `participate_email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `participate_password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `participate_phone` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `participate_sex` varchar(4) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `participate_idCard` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`participate_id`) USING BTREE,
  UNIQUE INDEX `AK_Key_2`(`participate_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pick_up
-- ----------------------------
DROP TABLE IF EXISTS `pick_up`;
CREATE TABLE `pick_up`  (
  `participate_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `Train_number` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `to_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `return_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_finish_pickup` tinyint(1) NOT NULL,
  PRIMARY KEY (`participate_id`, `driver_id`) USING BTREE,
  INDEX `FK_pick_up2`(`driver_id`) USING BTREE,
  CONSTRAINT `FK_pick_up` FOREIGN KEY (`participate_id`) REFERENCES `participant` (`participate_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_pick_up2` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
