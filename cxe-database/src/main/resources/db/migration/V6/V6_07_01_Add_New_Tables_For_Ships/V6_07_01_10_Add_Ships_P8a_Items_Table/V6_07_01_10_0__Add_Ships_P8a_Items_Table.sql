CREATE TABLE `proc_ships_category_items` (
  `sci_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `scc_ID` int(10) unsigned NOT NULL,
  `sci_title` varchar(255) DEFAULT NULL,
  `sci_title_footnote` TEXT DEFAULT NULL,
  `sci_description` TEXT DEFAULT NULL,
  `sci_parm_code` varchar(255) DEFAULT NULL,
  `sci_parm_code_footnote` TEXT DEFAULT NULL,
  `sci_competition` TEXT DEFAULT NULL,
  `sci_remarks` TEXT DEFAULT NULL,
  `sci_type` 
     enum
     (
        'P35',
        'MAJOR_ITEM',
        'COST_ELEMENT'
     ) NOT NULL,
  `total_cost_byd_ID` int(10) unsigned DEFAULT NULL,
  `quantity_byd_ID` int(10) unsigned DEFAULT NULL,
  `sci_is_significant` tinyint(1) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sci_ID`),
  KEY `FK_sci_scc_ID` (`scc_ID`),
  CONSTRAINT `FK_sci_scc_ID` FOREIGN KEY (`scc_ID`) REFERENCES `proc_ships_cost_cat` (`scc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



