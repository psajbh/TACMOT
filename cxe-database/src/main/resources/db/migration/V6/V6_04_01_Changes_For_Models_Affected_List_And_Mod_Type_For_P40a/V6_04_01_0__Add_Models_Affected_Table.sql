CREATE TABLE `proc_models_affected` (
  `ma_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pi_ID` int(10) unsigned DEFAULT NULL,
  `mg_ID` int(10) unsigned DEFAULT NULL,
  `ma_title` varchar(255) DEFAULT NULL,
  `ma_title_footnote` text,
  `ma_display_order` smallint(5) unsigned NOT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ma_ID`),
  KEY `FK_ma_pi_ID` (`pi_ID`),
  KEY `FK_ma_mg_ID` (`mg_ID`),
  CONSTRAINT `FK_ma_pi_ID` FOREIGN KEY (`pi_ID`) REFERENCES `proc_item` (`pi_ID`),
  CONSTRAINT `FK_ma_mg_ID` FOREIGN KEY (`mg_ID`) REFERENCES `proc_mod_grp` (`mg_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



