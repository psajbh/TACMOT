ALTER TABLE `proc_advance_rqmt` DROP COLUMN `lie_ID`,
  CHANGE COLUMN `ar_date_1st_sys_award` `ar_date_1st_sys_award` DATE NULL,
  CHANGE COLUMN `ar_date_1st_sys_complete` `ar_date_1st_sys_complete` DATE NULL,
  CHANGE COLUMN `ar_sys_interval` `ar_sys_interval` INT(10) UNSIGNED NULL,
  DROP INDEX `FK_ar_lie_ID` ;
