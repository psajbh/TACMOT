CREATE TABLE `proc_ships_class` (
  `sc_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sn_ID` int(10) unsigned DEFAULT NULL,
  `lie_ID` int(10) unsigned DEFAULT NULL,
  `net_byd_ID` int(10) unsigned DEFAULT NULL,
  `cec_tot_byd_ID` int(10) unsigned DEFAULT NULL,
  `sc_title` varchar(255) DEFAULT NULL,
  `sc_title_footnote` text DEFAULT NULL,
  `sc_bdgt_yrs_for_pdf` varchar(255) NOT NULL,
  `sc_ce_analysis_remarks` text DEFAULT NULL,
  `sc_ce_major_eqpmnt_remarks` text DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



