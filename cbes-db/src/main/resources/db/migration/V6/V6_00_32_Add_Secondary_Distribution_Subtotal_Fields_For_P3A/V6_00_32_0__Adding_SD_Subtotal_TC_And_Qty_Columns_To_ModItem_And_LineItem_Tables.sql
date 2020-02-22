ALTER TABLE `proc_mod_item` ADD COLUMN `mi_sec_dist_qty_byd_ID` INT(10) UNSIGNED NULL  AFTER `mi_compo_split_remarks`,
                           ADD COLUMN `mi_sec_dist_subtot_byd_ID` INT(10) UNSIGNED NULL  AFTER `mi_compo_split_remarks` ;                           
ALTER TABLE `proc_line_item` ADD COLUMN `li_sec_dist_qty_byd_ID` INT(10) UNSIGNED NULL  AFTER `li_compo_split_remarks`,
                             ADD COLUMN `li_sec_dist_subtot_byd_ID` INT(10) UNSIGNED NULL  AFTER `li_compo_split_remarks` ;