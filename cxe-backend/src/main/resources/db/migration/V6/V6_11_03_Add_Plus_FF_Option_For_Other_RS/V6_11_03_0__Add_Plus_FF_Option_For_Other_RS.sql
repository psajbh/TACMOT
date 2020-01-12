ALTER TABLE `proc_resource_summ_entry` 
CHANGE COLUMN `rse_plus_or_less` `rse_plus_or_less` ENUM('plus', 'less', 'plusff') NULL DEFAULT NULL COMMENT '' ;
