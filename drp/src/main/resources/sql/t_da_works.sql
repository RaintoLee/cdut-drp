/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.115
Source Server Version : 50722
Source Host           : 192.168.1.115:3306
Source Database       : drp

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-12 21:05:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_da_works
-- ----------------------------
DROP TABLE IF EXISTS `t_da_works`;
CREATE TABLE `t_da_works` (
  `works_id` varchar(20) NOT NULL COMMENT '作品ID',
  `usr_id` varchar(20) DEFAULT NULL COMMENT '自生成外键，人员信息表主键',
  `works_name` varchar(32) DEFAULT NULL COMMENT '作品名称',
  `works_spec` varchar(255) DEFAULT NULL COMMENT '作品大小',
  `works_format` varchar(10) DEFAULT NULL COMMENT 'avi、jpg、MP4、mp3、doc、txt....',
  `works_cate` varchar(10) DEFAULT NULL COMMENT '音频3，视频2，文档1，图片0',
  `works_memo` text COMMENT '作品摘要',
  `works_ischrg` tinyint(2) DEFAULT NULL COMMENT '00表示免费作品，01表示收费作品',
  `works_hdgt` decimal(14,2) DEFAULT NULL COMMENT '作品预估费用',
  `works_rgst_dt` date DEFAULT NULL COMMENT '作品注册日期',
  `works_rgst_tm` datetime DEFAULT NULL COMMENT '作品注册时间',
  `works_issu_dt` date DEFAULT NULL COMMENT '作品发布日期',
  `works_issu_tm` datetime DEFAULT NULL COMMENT '作品发布时间',
  `works_pageviews` varchar(20) DEFAULT NULL COMMENT '作品浏览量',
  `works_src` varchar(64) DEFAULT NULL COMMENT '作品来源',
  `works_store_src` varchar(200) DEFAULT NULL COMMENT '作品存储地址',
  `works_recinfo_src` varchar(200) DEFAULT NULL COMMENT '作品记录信息地址',
  `works_theme` varchar(32) DEFAULT NULL COMMENT '作品主题',
  `works_sym_key` varchar(32) DEFAULT NULL COMMENT '作品对称密钥',
  `works_secr_lvl` tinyint(2) DEFAULT NULL COMMENT '作品安全等级',
  `works_agent_qty` varchar(2) DEFAULT NULL COMMENT '作品代理数目 0-表示未代理，1-表示已代理',
  `works_auth_qty` varchar(2) DEFAULT NULL COMMENT '作品授权数目 0-表示未授权，1-表示已授权',
  `works_isvalid_flg` tinyint(2) DEFAULT NULL COMMENT '作品数据是否有效 0表示无效，1表示有效',
  PRIMARY KEY (`works_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
