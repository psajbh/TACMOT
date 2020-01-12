ALTER TABLE `proc_resource_summ_entry` ADD COLUMN `rse_plus_or_less` ENUM('plus','less') NULL DEFAULT NULL  AFTER `rse_ID_code` ;
