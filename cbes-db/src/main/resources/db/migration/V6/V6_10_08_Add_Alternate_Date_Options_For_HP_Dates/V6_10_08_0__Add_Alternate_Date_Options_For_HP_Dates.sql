ALTER TABLE `proc_history_and_planning` ADD COLUMN `hp_date_award_alternate` VARCHAR(40) NULL DEFAULT NULL  AFTER `hp_date_award` , 
                                        ADD COLUMN `hp_date_first_delivery_alternate` VARCHAR(40) NULL DEFAULT NULL  AFTER `hp_date_first_delivery` , 
                                        ADD COLUMN `hp_required_award_date_alternate` VARCHAR(40) NULL DEFAULT NULL  AFTER `hp_required_award_date` ;
