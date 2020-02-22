CREATE TABLE `proc_ships_analysis_cost_est` (
  `sace_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sc_ID` int(10) unsigned NOT NULL,
  `tlr_issue_ID` int(10) unsigned DEFAULT NULL,
  `tls_issue_ID` int(10) unsigned DEFAULT NULL,
  `prelim_design_ID` int(10) unsigned DEFAULT NULL,
  `contract_design_ID` int(10) unsigned DEFAULT NULL,
  `detail_design_ID` int(10) unsigned DEFAULT NULL,
  `rfp_ID` int(10) unsigned DEFAULT NULL,
  `sace_design_agent` varchar(255) DEFAULT NULL,
  `sace_design_agent_footnote` text DEFAULT NULL,
  `sace_classification` varchar(255) DEFAULT NULL,
  `sace_classification_footnote` text DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sace_ID`),
  KEY `FK_sace_sc_ID` (`sc_ID`),
  CONSTRAINT `FK_sace_sc_ID` FOREIGN KEY (`sc_ID`) REFERENCES `proc_ships_class` (`sc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



