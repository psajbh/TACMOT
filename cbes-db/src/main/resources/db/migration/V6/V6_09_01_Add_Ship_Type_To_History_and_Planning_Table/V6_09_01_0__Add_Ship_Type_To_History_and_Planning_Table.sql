ALTER TABLE `proc_history_and_planning` CHANGE COLUMN `hp_hull_number_footnote` `hp_hull_footnote` TEXT         NULL DEFAULT NULL  , 
                                        ADD    COLUMN `hp_hull_ship_type`                          VARCHAR(255) NULL DEFAULT NULL  AFTER `hp_hull_number` ;
