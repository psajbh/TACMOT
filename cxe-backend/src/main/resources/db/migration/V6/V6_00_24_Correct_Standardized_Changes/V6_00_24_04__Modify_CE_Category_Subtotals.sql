ALTER TABLE `proc_cost_elem_cat_exhibit`
 CHANGE COLUMN `cece_qty_byd_ID` `cece_tot_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `cece_UC_byd_ID`,
-- CHANGE COLUMN `unit_cost_proc_bdgt_yrs_data_fk` `cece_UC_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `cece_qty_byd_ID`,
 CHANGE COLUMN `cece_total_byd_ID` `cece_qty_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `cece_display_order`,
 DROP INDEX `FK_cece_qty_byd_ID`, 
 DROP INDEX `FK_cece_total_byd_ID`, 
 DROP FOREIGN KEY `FK_cece_qty_byd_ID`,
 DROP FOREIGN KEY `FK_cece_total_byd_ID`;
 
ALTER TABLE `proc_cost_elem_cat_exhibit` 
 ADD CONSTRAINT `FK_cece_qty_byd_ID` FOREIGN KEY `FK_cece_qty_byd_ID` (`cece_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_cece_tot_byd_ID` FOREIGN KEY `FK_cece_tot_byd_ID` (`cece_tot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;