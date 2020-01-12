CREATE TABLE `proc_ships_hulls` (
  `sh_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sco_ID` int(10) unsigned NOT NULL,
  `sh_number` varchar(255) DEFAULT NULL,
  `sh_number_footnote` text,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sh_ID`),
  KEY `FK_sh_sco_ID` (`sco_ID`),
  CONSTRAINT `FK_sh_sco_ID` FOREIGN KEY (`sco_ID`) REFERENCES `proc_ships_contracts` (`sco_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



