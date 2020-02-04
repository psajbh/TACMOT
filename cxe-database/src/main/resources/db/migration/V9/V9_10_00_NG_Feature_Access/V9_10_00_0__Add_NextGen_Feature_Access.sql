SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

drop table if exists FEATURE_ACCESS;

CREATE TABLE `FEATURE_ACCESS` (
  `FEATURE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT '0',
  `POINT_CUT` varchar(300) NOT NULL,
  `FEATURE_QUAL` int(1) NOT NULL,
  `EQUAL_LOGIC` BOOLEAN NOT NULL,
  `CREATED_BY_USER` varchar(256) DEFAULT NULL,
  `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY_USER` varchar(256) DEFAULT NULL,
  `DATE_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FEATURE_ID`),
  UNIQUE KEY `UN_ADVICE` (`POINT_CUT`)
) 
ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

