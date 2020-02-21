ALTER TABLE `proc_item_cat_40A` CHANGE COLUMN `total_cost_proc_bdgt_yrs_data_fk` `ic_qty_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ic_display_order`,
 CHANGE COLUMN `unit_cost_proc_bdgt_yrs_data_fk` `ic_UC_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ic_qty_byd_ID`,
 CHANGE COLUMN `quantity_proc_bdgt_yrs_data_fk` `ic_total_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ic_UC_byd_ID`,
 ADD CONSTRAINT `FK_ic_qty_byd_ID` FOREIGN KEY `FK_ic_qty_byd_ID` (`ic_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_ic_UC_byd_ID` FOREIGN KEY `FK_ic_UC_byd_ID` (`ic_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_ic_total_byd_ID` FOREIGN KEY `FK_ic_total_byd_ID` (`ic_total_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;