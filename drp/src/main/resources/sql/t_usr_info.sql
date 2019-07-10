/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : drp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-27 12:53:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_usr_info
-- ----------------------------
DROP TABLE IF EXISTS `t_usr_info`;
CREATE TABLE `t_usr_info` (
  `usr_id` varchar(64) NOT NULL COMMENT '人员序号，暂定uuid生成',
  `usr_nm` varchar(120) NOT NULL COMMENT '用户名，用户昵称、邮箱、手机',
  `usr_pwd` varchar(20) NOT NULL COMMENT '密码',
  `usr_gender` tinyint(2) DEFAULT NULL COMMENT '人员性别，0表示未知的性别，1表示男性，2表示女性，9表示未说明的性别',
  `usr_email` varchar(40) NOT NULL COMMENT '人员email，注册时激活账户和修改密码需要邮箱',
  `usr_phone` varchar(15) NOT NULL COMMENT '人员手机号',
  `usr_isMembership` tinyint(2) DEFAULT NULL COMMENT '是否是会员，0表示不是会员，1表示是会员',
  `usr_rgst_dt` date DEFAULT NULL COMMENT '注册日期',
  `usr_rgst_tm` datetime DEFAULT NULL COMMENT '注册时间',
  `usr_qlfy` varchar(2) DEFAULT NULL COMMENT '人员资质',
  `usr_qlfypic_src` varchar(255) DEFAULT NULL COMMENT '人员资质证书图片地址',
  `usr_stat` tinyint(2) DEFAULT NULL COMMENT '当前状态，00不在线，01在线',
  `usr_cert_cate` tinyint(2) DEFAULT NULL COMMENT '证件类型，0表示身份证，1表示户口簿，2表示护照，X其他证件',
  `usr_cert_num` varchar(20) DEFAULT NULL COMMENT '证件号码，',
  `usr_certpic_src` varchar(255) DEFAULT NULL COMMENT '存在图片服务器的url地址',
  `usr_addr` varchar(255) DEFAULT NULL COMMENT '人员地址',
  `usr_nicknm` varchar(60) DEFAULT NULL COMMENT '人员真实姓名',
  `usr_ava_src` varchar(200) DEFAULT NULL COMMENT '人员头像地址',
  `acct_bal` decimal(18,2) DEFAULT NULL COMMENT '平台账户余额',
  `pay_pwd` varchar(20) DEFAULT NULL COMMENT '平台账户支付密码',
  `usr_isvalid_flg` tinyint(2) DEFAULT NULL COMMENT '人员数据是否有效',
  PRIMARY KEY (`usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
