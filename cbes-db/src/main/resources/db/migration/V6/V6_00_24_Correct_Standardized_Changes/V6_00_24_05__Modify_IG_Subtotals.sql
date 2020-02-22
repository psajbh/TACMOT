ALTER TABLE `proc_item_group_40A`
 CHANGE COLUMN `ig_qty_byd_ID` `ig_tot_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ig_UC_byd_ID`,
-- CHANGE COLUMN `unit_cost_proc_bdgt_yrs_data_fk` `cece_UC_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `cece_qty_byd_ID`,
 CHANGE COLUMN `ig_total_byd_ID` `ig_qty_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ig_display_order`,
 DROP INDEX `FK_ig_qty_byd_ID`, 
 DROP INDEX `FK_ig_total_byd_ID`, 
 DROP FOREIGN KEY `FK_ig_qty_byd_ID`,
 DROP FOREIGN KEY `FK_ig_total_byd_ID`;
 
ALTER TABLE `proc_item_group_40A` 
 ADD CONSTRAINT `FK_ig_qty_byd_ID` FOREIGN KEY `FK_ig_qty_byd_ID` (`ig_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_ig_tot_byd_ID` FOREIGN KEY `FK_ig_tot_byd_ID` (`ig_tot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;  
   