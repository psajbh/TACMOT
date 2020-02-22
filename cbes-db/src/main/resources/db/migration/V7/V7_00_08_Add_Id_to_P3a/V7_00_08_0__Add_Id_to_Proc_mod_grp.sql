ALTER TABLE `proc_mod_grp` 
ADD COLUMN `mg_ID_code` ENUM('A','B') NULL DEFAULT NULL AFTER `mg_alternate_fp_flag`;