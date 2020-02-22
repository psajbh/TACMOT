-- Add column to identify a P-40A item as a mod item

ALTER TABLE `proc_item` MODIFY COLUMN `pi_mod_item` TINYINT(1) DEFAULT 0 AFTER `pi_ID_code`;