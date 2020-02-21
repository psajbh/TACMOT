CREATE TABLE `proc_mod_item_individual_install` (
  `imi_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `mi_ID` INT(10) UNSIGNED NOT NULL,
  `imi_qty_byd_ID` INT(10) UNSIGNED NULL,
  `imi_total_byd_ID` INT(10) UNSIGNED NULL,
  `imi_qty_install_byd_ID` INT(10) UNSIGNED NULL,
  `imi_tot_install_byd_ID` INT(10) UNSIGNED NULL,
  `imi_title` VARCHAR(255) NULL,
  `imi_remarks` TEXT NULL,
  `created_by_user` VARCHAR(255) NULL,  
  `date_created` timestamp NULL,
  `modified_by_user` VARCHAR(255) NULL,
  `date_modified` timestamp NULL,
  PRIMARY KEY (`imi_ID`));