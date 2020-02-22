CREATE TABLE `Mapping` (
  `mapping_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `raw_column` varchar(100) NOT NULL,
  `valid_column` varchar(100) NOT NULL,
  `type_id` int(11) unsigned DEFAULT NULL,
  `created_by_user` varchar(256) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by_user` varchar(256) DEFAULT NULL,
  `dare_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mapping_id`),
  CONSTRAINT `FK_PRCPMAPPING_TYPE_ID` FOREIGN KEY (`type_id`) REFERENCES `PRCP_TYPE` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Mapping`
(`mapping_id`,`raw_column`,`valid_column`,`type_id`)
VALUES
(1,'Account','account',1),
(2,'Account Title','accountTitle',1),
(3,'Organization','organization',1),
(4,'Budget Activity','budgetActivity',1),
(5,'Budget Activity Title','budgetActivityTitle',1),
(6,'Line Number','lineNumber',1),
(7,'BSA','budgetSubActivity',1),
(8,'Budget Sub Activity (BSA) Title','budgetSubActivityTitle',1),
(9,'Line Item','liNumber',1),
(10,'Line Item Title','liTitle',1),
(11,'Cost Type','costType',1),
(12,'Cost Type Title','costTypeTitle',1),
(13,'Add/ Non-Add','addNonAdd',1),
(14,'FY 2017 Total (Base + OCO) Quantity','pyQuantity',1),
(15,'FY 2017 Total (Base + OCO) Amount','pyAmount',1),
(16,'FY 2018 Base Quantity','cyQuantity',1),
(17,'FY 2018 Base Amount','cyAmount',1),
(18,'FY 2018 OCO Quantity','by1Quantity',1),
(19,'FY 2018 OCO Amount','by1Amount',1),
(20,'FY 2018 Total Quantity','by2Quantity',1),
(21,'FY 2018 Total Amount','by2Amount',1),
(22,'FY 2019 Base Quantity','by3Quantity',1),
(23,'FY 2019 Base Amount','by3Amount',1),
(24,'FY 2019 OCO Quantity','by4Quantity',1),
(25,'FY 2019 OCO Amount','by4Amount',1),
(26,'FY 2019 Total Quantity','by5Quantity',1),
(27,'FY 2019 Total Amount','by5Amount',1),
(28,'Classification','classification',1),
(29,'Account','account',2),
(30,'Account Title','accountTitle',2),
(31,'Organization','organization',2),
(32,'Budget Activity','budgetActivity',2),
(33,'Budget Activity Title','budgetActivityTitle',2),
(34,'Line Number','lineNumber',2),
(35,'PE / BLI','peNumber',2),
(36,'Program Element / Budget Line Item (BLI) Title','peTitle',2),
(37,'RDT&E Project','projectNumber',2),
(38,'RDT&E Project Title','projectTitle',2),
(39,'Fund Category','fundCategory',2),
(40,'FY 2019 Total (Base + OCO)','pyAmount',2),
(41,'FY 2018 Base','cyAmount',2),
(42,'FY 2018 OCO','by1Amount',2),
(43,'FY 2018 Total','by2Amount',2),
(44,'FY 2019 Base','by3Amount',2),
(45,'FY 2019 OCO','by4Amount',2),
(46,'FY 2019 Total','by5Amount',2),
(47,'Classification','classification',2);