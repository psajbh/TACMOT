ALTER TABLE `proc_line_item` 
CHANGE COLUMN `li_qty_unit_name` `li_qty_unit_name` ENUM('EACH','FEET','TONS','LOTS') NULL DEFAULT NULL ;
