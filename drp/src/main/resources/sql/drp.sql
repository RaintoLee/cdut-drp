
DROP DATABASE if exists drp;

CREATE database drp;

USE drp;

DROP TABLE IF EXISTS tb_users;
CREATE TABLE tb_users ( 
	`accountId` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
 	`createTime` datetime NOT NULL DEFAULT '1970-01-01',
 	`updateTime` datetime NOT NULL DEFAULT '1970-01-01',
 	`creatorId` INT NOT NULL DEFAULT 0,
 	`updatorId` INT NOT NULL DEFAULT 0,
 	`creatorName` VARCHAR (200) NULL DEFAULT '',
	`updatorName` VARCHAR (200) NULL DEFAULT '',
    PRIMARY KEY(`accountId`)
)DEFAULT CHARSET=utf8;

CREATE TABLE tb_downloads (
	`downloadId` INT NOT NULL AUTO_INCREMENT,
	`fileId` INT NOT NULL,
	`accountId` INT NOT NULL,
	`fileName` VARCHAR(500) NULL DEFAULT '',
	`fileType` VARCHAR(100) NULL DEFAULT '',
	`fileSize` INT NOT NULL DEFAULT 0,
 	`createTime` datetime NOT NULL DEFAULT '1970-01-01',
 	`updateTime` datetime NOT NULL DEFAULT '1970-01-01',
 	`creatorId` INT NOT NULL DEFAULT 0,
 	`updatorId` INT NOT NULL DEFAULT 0,
 	`creatorName` VARCHAR (200) NULL DEFAULT '',
	`updatorName` VARCHAR (200) NULL DEFAULT '',
    PRIMARY KEY(`downloadId`)
)DEFAULT CHARSET=utf8;

