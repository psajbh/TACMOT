ALTER TABLE `proc_line_item`
  MODIFY COLUMN `li_bdgt_yr` SMALLINT(5) UNSIGNED NULL  , 
  MODIFY COLUMN `li_bdgt_cycle` VARCHAR(10) NULL  , 
  MODIFY COLUMN `li_P1_title` VARCHAR(255) NULL  , 
  MODIFY COLUMN `li_P1_item_num` VARCHAR(25) NULL  , 
  MODIFY COLUMN `li_submit_date` DATE NULL  , 
  MODIFY COLUMN `li_unit_cost_format` ENUM('1','1000','1000000') NULL  , 
  MODIFY COLUMN `li_qty_format` ENUM('1','1000','1000000') NULL  , 
  MODIFY COLUMN `li_total_cost_format` ENUM('1','1000','1000000') NULL  ;
