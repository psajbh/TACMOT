ALTER TABLE `proc_line_item`
 ADD COLUMN `li_outyear_quantity_delta` INT(10) UNSIGNED NULL DEFAULT NULL AFTER `li_mods_out_years_delta`;