/*
Navicat MySQL Data Transfer

Source Server Version : 50636
Source Host           : 118.89.166.159:3306
Source Database       : probe

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-05-18 12:56:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activeness
-- ----------------------------
DROP TABLE IF EXISTS `activeness`;
CREATE TABLE `activeness` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `wifiProb` varchar(50) NOT NULL,
  `hour` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `numOfHighActive` int(11) DEFAULT NULL,
  `numOfMedianActive` int(11) DEFAULT NULL,
  `numOfLowActive` int(11) DEFAULT NULL,
  `numOfSleepActive` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `wifiProb` varchar(50) NOT NULL,
  `hour` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `inNoOutWifi` int(11) DEFAULT NULL,
  `inNoOutStore` int(11) DEFAULT NULL,
  `outNoInWifi` int(11) DEFAULT NULL,
  `outNoInStore` int(11) DEFAULT NULL,
  `inAndOutWifi` int(11) DEFAULT NULL,
  `intAndOutStore` int(11) DEFAULT NULL,
  `stayInWifi` int(11) DEFAULT NULL,
  `stayInStore` int(11) DEFAULT NULL,
  `jumpRate` double DEFAULT NULL,
  `deepVisit` double DEFAULT NULL,
  `inStoreRate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for new_old
-- ----------------------------
DROP TABLE IF EXISTS `new_old`;
CREATE TABLE `new_old` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `wifiProb` varchar(50) NOT NULL,
  `hour` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `newCustomer` int(11) DEFAULT NULL,
  `oldCustomer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for probe
-- ----------------------------
DROP TABLE IF EXISTS `probe`;
CREATE TABLE `probe` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `probeId` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for store_hours
-- ----------------------------
DROP TABLE IF EXISTS `store_hours`;
CREATE TABLE `store_hours` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `wifiProb` varchar(50) NOT NULL,
  `hour` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data0` int(11) DEFAULT NULL,
  `data1` int(11) DEFAULT NULL,
  `data2` int(11) DEFAULT NULL,
  `data3` int(11) DEFAULT NULL,
  `data4` int(11) DEFAULT NULL,
  `data5` int(11) DEFAULT NULL,
  `data6` int(11) DEFAULT NULL,
  `data7` int(11) DEFAULT NULL,
  `data8` int(11) DEFAULT NULL,
  `data9` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','user') DEFAULT 'admin',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_UNIQUE_INDEX` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visit_circle
-- ----------------------------
DROP TABLE IF EXISTS `visit_circle`;
CREATE TABLE `visit_circle` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `wifiProb` varchar(50) NOT NULL,
  `hour` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data0` int(11) DEFAULT NULL,
  `data1` int(11) DEFAULT NULL,
  `data2` int(11) DEFAULT NULL,
  `data3` int(11) DEFAULT NULL,
  `data4` int(11) DEFAULT NULL,
  `data5` int(11) DEFAULT NULL,
  `data6` int(11) DEFAULT NULL,
  `data7` int(11) DEFAULT NULL,
  `data8` int(11) DEFAULT NULL,
  `data9` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
