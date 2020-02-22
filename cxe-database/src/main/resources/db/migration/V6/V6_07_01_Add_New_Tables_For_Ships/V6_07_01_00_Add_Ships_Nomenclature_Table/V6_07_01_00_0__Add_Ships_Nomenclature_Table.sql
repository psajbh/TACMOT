CREATE TABLE `proc_ships_nomenclature` (
  `sn_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sn_type` varchar(255) DEFAULT NULL,
  `sn_classification` varchar(255) DEFAULT NULL,
  `sn_first_hull_number` varchar(255) DEFAULT NULL,
  `sn_remarks` TEXT DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sn_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



