ALTER TABLE `proc_line_item`
 ADD COLUMN `li_pys_quantity_delta` DECIMAL(13,3) UNSIGNED NULL DEFAULT NULL AFTER `li_pys_delta`;