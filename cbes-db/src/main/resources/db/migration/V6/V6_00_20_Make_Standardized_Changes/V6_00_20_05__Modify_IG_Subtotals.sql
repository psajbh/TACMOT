ALTER TABLE `proc_item_group_40A` CHANGE COLUMN `total_cost_proc_bdgt_yrs_data_fk` `ig_qty_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ig_display_order`,
 CHANGE COLUMN `unit_cost_proc_bdgt_yrs_data_fk` `ig_UC_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ig_qty_byd_ID`,
 CHANGE COLUMN `quantity_proc_bdgt_yrs_data_fk` `ig_total_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `ig_UC_byd_ID`,
 ADD CONSTRAINT `FK_ig_qty_byd_ID` FOREIGN KEY `FK_ig_qty_byd_ID` (`ig_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_ig_UC_byd_ID` FOREIGN KEY `FK_ig_UC_byd_ID` (`ig_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_ig_total_byd_ID` FOREIGN KEY `FK_ig_total_byd_ID` (`ig_total_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
   