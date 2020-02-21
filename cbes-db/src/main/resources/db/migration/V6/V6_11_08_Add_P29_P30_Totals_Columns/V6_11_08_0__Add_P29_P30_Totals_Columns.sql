ALTER TABLE `proc_ships_outfitting_delivery_exhibit` 
ADD COLUMN `p29_outfitting_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '' AFTER `date_modified`,
ADD COLUMN `p30_delivery_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '' AFTER `p29_outfitting_byd_ID`,
ADD COLUMN `p30_destination_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '' AFTER `p30_delivery_byd_ID`;