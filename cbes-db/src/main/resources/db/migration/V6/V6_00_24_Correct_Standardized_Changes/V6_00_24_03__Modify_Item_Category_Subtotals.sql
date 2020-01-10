ALTER TABLE `proc_item_cat_40A`
 CHANGE COLUMN `ic_qty_byd_ID` `ic_tot_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ic_UC_byd_ID`,
-- CHANGE COLUMN `unit_cost_proc_bdgt_yrs_data_fk` `ic_UC_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ic_qty_byd_ID`,
 CHANGE COLUMN `ic_total_byd_ID` `ic_qty_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ic_display_order`,
 DROP INDEX `FK_ic_qty_byd_ID`, 
 DROP INDEX `FK_ic_total_byd_ID`, 
 DROP FOREIGN KEY `FK_ic_qty_byd_ID`,
 DROP FOREIGN KEY `FK_ic_total_byd_ID`;
 
ALTER TABLE `proc_item_cat_40A` 
 ADD CONSTRAINT `FK_ic_qty_byd_ID` FOREIGN KEY `FK_ic_qty_byd_ID` (`ic_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_ic_tot_byd_ID` FOREIGN KEY `FK_ic_tot_byd_ID` (`ic_tot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;