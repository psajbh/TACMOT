CREATE TABLE `proc_ships_outfitting_delivery_cost_elem` (
  `sodc_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Ships Outfitting and Delivery Cost element primary key.',
  `sodt_ID` INT(10) UNSIGNED NOT NULL COMMENT 'Foreign key link back to the cost type data, the overall ship class this cost element belongs to.',
  `sco_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key to a ships contract data entry.',
  `outfitting_byd_ID` INT(10) UNSIGNED NULL COMMENT 'Foreign key to a standard costs entry to represent outfitting costs.',
  `delivery_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key to a standard costs entry to represent delivery costs.',
  `sodc_pubs_program_year` INT(4) UNSIGNED NULL DEFAULT NULL COMMENT 'The Publications Program Year for this single Cost element if of Pubs type.',
  `sodc_pubs_program_year_footnote` TEXT NULL DEFAULT NULL COMMENT 'Footnote information for the hull number.',
  `date_created` TIMESTAMP NULL DEFAULT NULL,
  `created_by_user` VARCHAR(255) NULL DEFAULT NULL,
  `modified_by_user` VARCHAR(255) NULL DEFAULT NULL,
  `date_modified` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`sodc_ID`),
  INDEX `FK_sodt_ID_idx` (`sodt_ID` ASC),
  CONSTRAINT `FK_sodt_ID`
    FOREIGN KEY (`sodt_ID`)
    REFERENCES `proc_ships_outfitting_delivery_cost_type` (`sodt_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Table which holds information about a single ship\'s outfitting and delivery costs:  Hull Number, Contract Data, Outfitting Cost, Delivery Cost.  Also used to represent the overall Publications and First Destination overall costs for each FY';
