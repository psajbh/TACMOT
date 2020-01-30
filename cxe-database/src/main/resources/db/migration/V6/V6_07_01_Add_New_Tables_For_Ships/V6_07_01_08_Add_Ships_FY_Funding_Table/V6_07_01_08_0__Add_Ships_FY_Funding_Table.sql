CREATE TABLE `proc_ships_fy_funding` (
  `sfyf_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sc_ID` int(10) unsigned NOT NULL,
  `sfyf_fiscal_year` int(4) DEFAULT NULL,
  `sfyf_fiscal_year_footnote` TEXT DEFAULT NULL,
  `funding_byd_ID` int(10) unsigned DEFAULT NULL,
  `sfyf_type` 
     enum
     (
        'ADVANCE_PROCUREMENT',
        'SUBSEQUENT_YEAR_FULL_FUNDING',
        'COST_TO_COMPLETE'
     ) NOT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sfyf_ID`),
  KEY `FK_sfyf_sc_ID` (`sc_ID`),
  CONSTRAINT `FK_sfyf_sc_ID` FOREIGN KEY (`sc_ID`) REFERENCES `proc_ships_class` (`sc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



