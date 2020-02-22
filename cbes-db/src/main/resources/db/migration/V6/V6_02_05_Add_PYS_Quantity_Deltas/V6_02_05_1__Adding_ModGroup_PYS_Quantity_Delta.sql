ALTER TABLE `proc_mod_grp`
 ADD COLUMN `mg_pys_quantity_delta` DECIMAL(13,3) UNSIGNED NULL DEFAULT NULL AFTER `mg_pys_delta`;