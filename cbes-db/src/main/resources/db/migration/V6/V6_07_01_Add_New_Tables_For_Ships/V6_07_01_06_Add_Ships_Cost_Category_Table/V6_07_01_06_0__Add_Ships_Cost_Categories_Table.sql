CREATE TABLE `proc_ships_cost_cat` (
  `scc_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sc_ID` int(10) unsigned NOT NULL,
  `scc_name` varchar(255) DEFAULT NULL,
  `scc_category` 
     enum
     (
        'PLAN_COSTS',
        'BASIC_CONSTRUCTION_CONVERSION',
        'CHANGE_ORDERS',
        'ELECTRONICS',
        'TECHNOLOGY_INSERTION',
        'PROPULSION_EQUIPMENT',
        'HME',
        'ORDINANCE',
        'ESCALATION',
        'OTHER_COST',
        'USER_DEFINED'
     ) DEFAULT NULL,
  `tot_byd_ID` int(10) unsigned DEFAULT NULL,
  `qty_byd_ID` int(10) unsigned DEFAULT NULL,
  `major_item_tot_byd_ID` int(10) unsigned DEFAULT NULL,
  `p35_tot_byd_ID` int(10) unsigned DEFAULT NULL,
  `other_ce_tot_byd_ID` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`scc_ID`),
  KEY `FK_scc_sc_ID` (`sc_ID`),
  KEY `FK_scc_tot_byd_ID` (`tot_byd_ID`),
  KEY `FK_scc_qty_byd_ID` (`qty_byd_ID`),
  KEY `FK_scc_mi_tot_byd_ID` (`major_item_tot_byd_ID`),
  KEY `FK_scc_p35_tot_byd_ID` (`p35_tot_byd_ID`),
  KEY `FK_scc_other_ce_tot_byd_ID` (`other_ce_tot_byd_ID`),
  CONSTRAINT `FK_scc_sc_ID` FOREIGN KEY (`sc_ID`) REFERENCES `proc_ships_class` (`sc_ID`),
  CONSTRAINT `FK_scc_mi_tot_byd_ID` FOREIGN KEY (`major_item_tot_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`),
  CONSTRAINT `FK_scc_p35_tot_byd_ID` FOREIGN KEY (`p35_tot_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`),
  CONSTRAINT `FK_scc_other_ce_tot_byd_ID` FOREIGN KEY (`other_ce_tot_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`),
  CONSTRAINT `FK_scc_tot_byd_ID` FOREIGN KEY (`tot_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`),
  CONSTRAINT `FK_scc_qty_byd_ID` FOREIGN KEY (`qty_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



