-- Adding a One to One mapping to Item
TRUNCATE TABLE proc_advance_bdgt_just;
TRUNCATE TABLE proc_advance_rqmt;
ALTER TABLE `proc_advance_rqmt` CHANGE COLUMN `ar_ID` `ar_ID` INT(10) UNSIGNED NOT NULL;
