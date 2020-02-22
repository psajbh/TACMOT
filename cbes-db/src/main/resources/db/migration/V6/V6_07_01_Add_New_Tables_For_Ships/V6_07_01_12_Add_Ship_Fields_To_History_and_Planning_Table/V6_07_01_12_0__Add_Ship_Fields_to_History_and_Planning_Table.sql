ALTER TABLE `proc_history_and_planning`
      ADD COLUMN `hp_hull_number` VARCHAR(255) DEFAULT NULL  AFTER `hp_remarks` , 
      ADD COLUMN `hp_hull_number_footnote` TEXT DEFAULT NULL  AFTER `hp_hull_number` ,
      ADD COLUMN `hp_new_option` ENUM('NEW', 'OPTION') DEFAULT NULL  AFTER `hp_hull_number_footnote` ,
      ADD COLUMN `hp_months_required` INT(10) DEFAULT NULL  AFTER `hp_new_option` , 
      ADD COLUMN `hp_months_required_footnote` TEXT DEFAULT NULL  AFTER `hp_months_required` , 
      ADD COLUMN `hp_required_award_date` DATE DEFAULT NULL  AFTER `hp_months_required_footnote` , 
      ADD COLUMN `hp_required_award_date_footnote` TEXT DEFAULT NULL  AFTER `hp_required_award_date` ;
