ALTER TABLE `proc_ships_outfitting_delivery_cost_elem` 
DROP COLUMN `sodc_pubs_program_year_footnote`,
DROP COLUMN `sodc_pubs_program_year`,
ADD COLUMN `sodc_hull_number` INT(6) UNSIGNED NULL DEFAULT NULL COMMENT 'The hull number for this single cost element if of a ship type.' AFTER `delivery_byd_ID`,
ADD COLUMN `sodc_hull_number_footnote` TEXT NULL DEFAULT NULL COMMENT 'Footnote information for the Hull Number.' AFTER `sodc_hull_number`,
ADD COLUMN `sodc_program_year` INT(4) UNSIGNED NULL DEFAULT NULL COMMENT 'The Program Year for this single Cost element.' AFTER `sodc_hull_number_footnote`,
ADD COLUMN `sodc_program_year_footnote` TEXT NULL DEFAULT NULL COMMENT 'Footnote information for the Program Year.' AFTER `sodc_program_year`,
ADD COLUMN `sodc_element_type` ENUM('PUBS','FIRST_DESTINATION','SHIP_CLASS') NOT NULL COMMENT 'Enumeration of the three different types of Cost Types allowed under a P-29/P-30:  a normal ship class with a list of hull, the First Destination costs for completed ships that year, and the Publication costs for that year.' AFTER `sodc_program_year_footnote`;
