CREATE TABLE `proc_ships_initial_reissue_dates` (
  `sird_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sird_initial_start` date DEFAULT NULL,
  `sird_initial_start_footnote` text,
  `sird_initial_complete` date DEFAULT NULL,
  `sird_initial_complete_footnote` text,
  `sird_reissue_start` date DEFAULT NULL,
  `sird_reissue_start_footnote` text,
  `sird_reissue_complete` date DEFAULT NULL,
  `sird_reissue_complete_footnote` text,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sird_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



