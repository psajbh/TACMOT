CREATE TABLE `proc_ships_p35_categories` (
  `spc_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sci_ID` int(10) unsigned NOT NULL,
  `spc_title` varchar(255) DEFAULT NULL,
  `spc_title_footnote` TEXT DEFAULT NULL,
  `spc_category` 
     enum
     (
        'MAJOR_HARDWARE',
        'ANCILLARY_EQUIPMENT',
        'TECHNICAL_DATA_AND_DOCUMENTATION',
        'SPARES',
        'SYSTEM_ENGINEERING',
        'TECHNICAL_ENGINEERING_SERVICES',
        'ASSEMBLY_AND_INTEGRATION',
        'OTHER_COSTS',
        'USER_DEFINED'
     ) NOT NULL,
  `total_cost_byd_ID` int(10) unsigned DEFAULT NULL,
  `quantity_byd_ID` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`spc_ID`),
  KEY `FK_spc_sci_ID` (`sci_ID`),
  CONSTRAINT `FK_spc_sci_ID` FOREIGN KEY (`sci_ID`) REFERENCES `proc_ships_category_items` (`sci_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



