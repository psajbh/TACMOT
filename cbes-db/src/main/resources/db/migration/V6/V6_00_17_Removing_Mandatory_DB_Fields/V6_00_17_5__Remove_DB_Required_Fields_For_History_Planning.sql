ALTER TABLE `proc_history_and_planning`
  MODIFY COLUMN `hp_fy` INT(10) UNSIGNED NULL  , 
  MODIFY COLUMN `hp_PCO_location` VARCHAR(100) NULL  , 
  MODIFY COLUMN `hp_date_award` DATE NULL  , 
  MODIFY COLUMN `hp_date_first_delivery` DATE NULL  , 
  MODIFY COLUMN `hp_qty` INT(10) UNSIGNED NULL  , 
  MODIFY COLUMN `hp_unit_cost` DECIMAL(15,3) NULL  ;
