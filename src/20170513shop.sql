/*
Navicat MySQL Data Transfer

Source Server         : MYSQL
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-05-13 11:13:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'gcl', 'gcl');

-- ----------------------------
-- Table structure for cartitem
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem` (
  `count` int(255) DEFAULT NULL,
  `subTotal` double(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cartitem
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '二手车');

-- ----------------------------
-- Table structure for categorysecond
-- ----------------------------
DROP TABLE IF EXISTS `categorysecond`;
CREATE TABLE `categorysecond` (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `FK936FCAF2169FA4BD` (`cid`),
  CONSTRAINT `FK936FCAF2169FA4BD` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of categorysecond
-- ----------------------------
INSERT INTO `categorysecond` VALUES ('1', '二手单车', '1');
INSERT INTO `categorysecond` VALUES ('2', '二手汽车', '1');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double(255,0) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `FKE8B2AB618B9739C0` (`pid`),
  KEY `FKE8B2AB61B2E09417` (`oid`),
  CONSTRAINT `FKE8B2AB618B9739C0` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `FKE8B2AB61B2E09417` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', '5', '330', '1', '1');
INSERT INTO `orderitem` VALUES ('2', '1', '66', '1', '3');
INSERT INTO `orderitem` VALUES ('3', '4', '264', '2', '4');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `totalMoney` double(255,0) DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userphone` varchar(255) DEFAULT NULL,
  `useraddr` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKC3DF62E521794F69` (`uid`),
  CONSTRAINT `FKC3DF62E521794F69` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', null, '2017-05-13 00:44:11', '0', '沈东诚', '13192344270', '四渡赤水独处', '330', '1');
INSERT INTO `orders` VALUES ('2', null, '2017-05-13 00:44:33', '0', '沈东诚', '13192344270', '四渡赤水独处', '0', '1');
INSERT INTO `orders` VALUES ('3', null, '2017-05-13 00:45:03', '0', '沈东诚', '13192344270', '四渡赤水独处', '66', '1');
INSERT INTO `orders` VALUES ('4', null, '2017-05-13 09:26:14', '0', '沈东诚', '13192344270', '四渡赤水独处', '264', '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  `market_price` double(10,2) DEFAULT NULL,
  `shop_price` double(10,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `is_hot` int(255) DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `csid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FKED8DCCEF74BA72D2` (`csid`),
  CONSTRAINT `FKED8DCCEF74BA72D2` FOREIGN KEY (`csid`) REFERENCES `categorysecond` (`csid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '牛逼哄哄的许宏达2', '666.00', '66.00', 'products/piero_lissoni.jpg', '  牛逼哄哄的许宏达牛逼哄哄的许宏达牛逼哄哄的许宏达牛逼哄哄的许宏达牛逼哄哄的许宏达', '0', '2017-05-13 09:39:53', null);
INSERT INTO `product` VALUES ('2', '牛逼哄哄的许宏达1', '666.00', '66.00', 'products/piero_lissoni.jpg', ' 牛逼哄哄的许宏达牛逼哄哄的许宏达牛逼哄哄的许宏达牛逼哄哄的许宏达牛逼哄哄的许宏达', '1', '2017-05-13 09:38:17', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sdc', 'sdc', '沈东诚', 'male', '739638270@qq.com', '13192344270', '四渡赤水独处', '1', 'adf1279a70a243a59a179eefaa7c44021c148fff7510456a8b09447170559971');
INSERT INTO `user` VALUES ('2', '123456', '123456', '123456', 'male', '739638270@qq.com', '13192344270', '四渡赤水独处', '1', 'f979cef477f3429897e6d7a329dd8e957b98e06675cb434fa1f9911ed4e4fba1');
