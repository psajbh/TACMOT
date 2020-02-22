CREATE TABLE `proc_ships_by_for_pdf` (
  `sbyfp_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sc_ID` int(10) unsigned NOT NULL,
  `sbyfp_budget_year` int(10) NOT NULL,
  `sbyfp_p8a_p35_flag` tinyint(1),
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sbyfp_ID`),
  KEY `FK_sbyfp_sc_ID` (`sc_ID`),
  CONSTRAINT `FK_sbyfp_sc_ID` FOREIGN KEY (`sc_ID`) REFERENCES `proc_ships_class` (`sc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



