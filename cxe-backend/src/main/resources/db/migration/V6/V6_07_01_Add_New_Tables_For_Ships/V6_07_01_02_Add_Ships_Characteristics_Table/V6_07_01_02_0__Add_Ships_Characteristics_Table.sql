CREATE TABLE `proc_ships_characteristics` (
  `sch_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sc_ID` int(10) unsigned NOT NULL,
  `sch_length` decimal(13,3) DEFAULT NULL,
  `sch_length_footnote` TEXT DEFAULT NULL,
  `sch_beam` decimal(13,3) DEFAULT NULL,
  `sch_beam_footnote` TEXT DEFAULT NULL,
  `sch_displacement` decimal(13,3) DEFAULT NULL,
  `sch_displacement_footnote` TEXT DEFAULT NULL,
  `sch_draft` decimal(13,3) DEFAULT NULL,
  `sch_draft_footnote` TEXT DEFAULT NULL,
  `sch_speed` decimal(13,3) DEFAULT NULL,
  `sch_speed_footnote` TEXT DEFAULT NULL,
  `sch_installed_power` decimal(13,3) DEFAULT NULL,
  `sch_installed_power_footnote` TEXT DEFAULT NULL,
  `sch_crew_size` decimal(13,3) DEFAULT NULL,
  `sch_crew_size_footnote` TEXT DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sch_ID`),
  KEY `FK_sch_sc_ID` (`sc_ID`),
  CONSTRAINT `FK_sch_sc_ID` FOREIGN KEY (`sc_ID`) REFERENCES `proc_ships_class` (`sc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



