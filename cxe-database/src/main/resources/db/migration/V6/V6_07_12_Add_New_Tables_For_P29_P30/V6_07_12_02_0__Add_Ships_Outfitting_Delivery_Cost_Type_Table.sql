CREATE TABLE `proc_ships_outfitting_delivery_cost_type` (
  `sodt_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key for the table.',
  `sod_ID` INT(10) UNSIGNED NOT NULL COMMENT 'Foreign key link to the Outfitting and Delivery budget exhibit that contains information for this class of ship.',
  `sn_ID` INT(10) UNSIGNED NULL COMMENT 'Foreign key link to a ship nomenclature entry that will contain the class, classification, and name information for this ship class.',
  `outfitting_total_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key link to a standard costs entry for the overall ship class totals for outfitting costs.',
  `delivery_total_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key link to a standard costs entry for the overall ship class totals for delivery costs.',
  `sodt_category` ENUM('PUBS','FIRST_DESTINATION','SHIP_CLASS') NOT NULL COMMENT 'Enumeration of the three different types of Cost Types allowed under a P-29/P-30:  a normal ship class with a list of hull, the First Destination costs for completed ships that year, and the Publication costs for that year.',
  `date_created` TIMESTAMP NULL DEFAULT NULL,
  `created_by_user` VARCHAR(255) NULL DEFAULT NULL,
  `modified_by_user` VARCHAR(255) NULL DEFAULT NULL,
  `date_modified` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`sodt_ID`),
  INDEX `FK_sod_ID_idx` (`sod_ID` ASC),
  CONSTRAINT `FK_sod_ID`
    FOREIGN KEY (`sod_ID`)
    REFERENCES `proc_ships_outfitting_delivery_exhibit` (`sod_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Table which references all details about the outfitting and delivery iinformation for a class of ships: name of the ship class and type plus a list of the contract and cost data for each individual ship of that class with outfitting or delivery costs.';
