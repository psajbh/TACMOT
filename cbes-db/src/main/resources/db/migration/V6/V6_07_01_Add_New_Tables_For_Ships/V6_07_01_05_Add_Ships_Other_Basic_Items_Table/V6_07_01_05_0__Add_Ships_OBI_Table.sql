CREATE TABLE `proc_ships_other_basic_items` (
  `obi_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sco_ID` int(10) unsigned NOT NULL,
  `obi_name` varchar(255) DEFAULT NULL,
  `obi_name_footnote` text,
  `obi_amount` int(10) DEFAULT NULL,
  `obi_amount_footnote` text,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`obi_ID`),
  KEY `FK_obi_sco_ID` (`sco_ID`),
  CONSTRAINT `FK_obi_sco_ID` FOREIGN KEY (`sco_ID`) REFERENCES `proc_ships_contracts` (`sco_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



