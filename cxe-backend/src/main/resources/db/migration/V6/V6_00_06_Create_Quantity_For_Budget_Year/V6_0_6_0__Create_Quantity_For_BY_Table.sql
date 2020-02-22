create table `proc_quantity_for_budget_year` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `quantity` decimal(13,3),
  `quantity_footnote` TEXT,
  `budget_year` smallint(5) UNSIGNED,
  `budget_year_footnote` TEXT,
   abj_costs_byd_fk INT(10) UNSIGNED,
  `proc_advance_bdgt_just_fk` INT UNSIGNED,
  `created_by_user` varchar(255) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT '2010-05-03 13:13:28',
  `modified_by_user` varchar(255) DEFAULT NULL,
  `date_modified` timestamp NOT NULL DEFAULT '2010-05-03 13:13:28',
  PRIMARY KEY(`id`)
);

--alter table proc_quantity_for_budget_year add column abj_costs_byd_fk INT(10) UNSIGNED;
